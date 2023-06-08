import hudson.*
import hudson.model.*

[
        [
                name       : 'claim-internal-audit-api',
                description: '''Builds the claim-internal-audit-api artifact, deploys to Artifactory, runs chef-client on DEPLOY_HOST''',
                repo       : '<GIT REPO SSH URL FOR YOUR PROJECT>',
                browserUrl : '<BROWSE URL FOR YOU GIT REPO e.g https://git.vspglobal.com/projects/EA/repos/app-generator/browse>',
                branch     : 'master',
                numToKeep  : 5,
                artifactory: 'true',
                jdk        : 'Java 8',
                pom        : 'pom.xml',
                version    : '1.${BUILD_NUMBER}',
                artifactoryUrl: 'http://artifactory.vsp.com',
                mavenVersion: '3.3.x',
                emailList: ['<TEAM MEMBER 1>', '<TEAM MEMBER 2>'],
                deployHost: '<DEFAULT DEPLOY HOST>'
        ]
].each { config ->

    mavenJob(config.name) {

        description(config.description)

        parameters {
            stringParam('DEPLOY_HOST', config.deployHost, 'The host to run chef-client on')
        }

        scm {
            git {
                remote {
                    name('origin')
                    url(config.repo)
                    credentials('jenkins')
                    branch(config.branch)
                }
                browser {
                    stash(config.browserUrl)
                }
            }
        }

        triggers {
            scm('@yearly')
        }

        jdk(config.jdk)

        preBuildSteps {

            maven {
                mavenInstallation('Maven ' + config.mavenVersion)
                goals("versions:set -DnewVersion=$config.version")
                rootPOM(config.pom)
            }

        }

        postBuildSteps {
            shell("""ssh -oStrictHostKeyChecking=no -l root \${DEPLOY_HOST} "chef-client" --no-color""".stripIndent())

        }

        rootPOM(config.pom)
        goals('--update-snapshots clean install')

        localRepository(LocalRepositoryLocation.LOCAL_TO_WORKSPACE)

        wrappers {
            preBuildCleanup()
            maskPasswords()
            timestamps()
            injectPasswords {
                injectGlobalPasswords()
            }

        }

        properties {
            logRotator {
                numToKeep(config.numToKeep)
                daysToKeep(-1)
            }
        }

        configure { proj ->
            if (config.artifactory != null) {
                proj / buildWrappers << 'org.jfrog.hudson.maven3.ArtifactoryMaven3NativeConfigurator' {
                    details {
                        artifactoryName('artifactory.vsp.com')
                        artifactoryUrl(config.artifactoryUrl)
                        resolveReleaseRepository {
                            keyFromText('repo')
                            keyFromSelect('repo')
                            dynamicMode(false)
                        }
                        resolveSnapshotRepository {
                            keyFromText('repo')
                            keyFromSelect('repo')
                            dynamicMode(false)
                        }
                    }
                }
                proj / publishers << 'org.jfrog.hudson.ArtifactoryRedeployPublisher' {
                    details {
                        artifactoryName('artifactory.vsp.com')
                        artifactoryUrl(config.artifactoryUrl)
                        deployReleaseRepository {
                            keyFromText('apps-staging-local')
                            keyFromSelect('apps-staging-local')
                            dynamicMode(false)
                        }
                        deploySnapshotRepository {
                            keyFromText('apps-snapshot-local')
                            keyFromSelect('apps-snapshot-local')
                            dynamicMode(false)
                        }
                    }
                    deployBuildInfo(true)
                    deployArtifacts(true)
                    discardOldBuilds(true)
                    discardBuildArtifacts(true)
                    evenIfUnstable(false)
                    envVarsPatterns {
                        includePatterns()
                        excludePatterns('*password,*secret*')
                    }
                }
            }
        }

		configure{ proj ->
        	proj / postbuilders << 'hudson.plugins.sonar.SonarRunnerBuilder'{
        		project 'sonar.properties'
             	properties ''
             	jdk 'Java 8'
             	sonarScannerName 'Sonar Runner 3.3'
        	}
    	}
        publishers {
            // push tag with release version to Git
            gitPublisher {
                pushMerge(false)
                forcePush(false)
                pushOnlyIfSuccess(true)
                tagsToPush {
                    tagToPush {
                        targetRepoName('origin')
                        tagName("release-$config.version")
                        tagMessage("Release $config.version")
                        createTag(true)
                        updateTag(false)
                    }
                }
            }

            extendedEmail {
                attachBuildLog()

                for (email in config.emailList) {
                    recipientList(email)
                }

                defaultSubject('$DEFAULT_SUBJECT')
                defaultContent('$DEFAULT_CONTENT')
                contentType('text/html')
                triggers {
                    success {
                        subject('$PROJECT_DEFAULT_SUBJECT')
                        content('$PROJECT_DEFAULT_CONTENT')
                        sendTo {
                            developers()
                            requester()
                            culprits()
                        }
                    }
                    failure {
                        subject('$PROJECT_DEFAULT_SUBJECT')
                        content('$PROJECT_DEFAULT_CONTENT')
                        sendTo {
                            developers()
                            requester()
                            culprits()
                        }
                    }
                    unstable {
                        subject('$PROJECT_DEFAULT_SUBJECT')
                        content('$PROJECT_DEFAULT_CONTENT')
                        sendTo {
                            developers()
                            requester()
                            culprits()
                        }
                    }
                }
            }
        }

    }
}
