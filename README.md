This is a placeholder for your README.md file, please replace with useful documentation.

# WARNING
Under no circumstances should you create or need a `bootstrap.yaml` file in `src/main/resources`. This will override settings built
into API Cloud Starter and cause OAuth issues and other unexpected behavior.

## API Cloud Starter Resource Center
See [API Cloud Starter Documentation](https://confluence.vspglobal.com/x/oYSTAQ) in Confluence.

## Consul Service Discovery
When running the application on your local machine, you'll need to disable Consul as there is no need to have it running locally.
To disable Consul, run the application with `-Dspring.cloud.consul.enabled=false`.

## Externalized Configuration with Consul
To externalize the configuration for your application, see the [Application Configuration](https://confluence.vspglobal.com/x/KL9pAg) document.

## Health Indicators
Some [Health Indicators](https://git.vspglobal.com/projects/API/repos/api-cloud-starter/browse/vsp-health-indicators/src/main/java/com/vspglobal/cloud/health) are included by default in api-cloud-starter.
If you wish to add your own custom health indicators, see Spring Boot's documentation on [writing custom health indicators](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html#_writing_custom_healthindicators).
#### WARNING:
Health checks are run every 30 seconds. Depending on what your custom health indicator is doing, you may see a performance impact on your application.

## Creating a Jenkins Immutable Deployment Pipeiline (JIDP)
jenkinsidp.properties is provided in the root of your project to facilitate executing a JIDP DEV/TEST/STG/SBOX iipeline jobs.
Replace the values with the angled brackets in jenkinsidp.properties file appropriately.
If createJidp option was selected, your team's seed repo has been updated to include DEV/STG/SBOX pipelines. Update this json block with repo url
branch, maven goals..etc. [Read more about JIDP](https://confluence.vspglobal.com/x/L0V3Ag). Contact SWES TEAM on slack for any JIDP support.


## Creating a Jenkins Job
A Jenkins job seed DSL groovy file has been provided at the root of your project. Replace the values with the angled brackets at
the top of the file. Follow the [instructions for creating a seed job](https://confluence.vspglobal.com/x/FK5LAg). The generated
Jenkins job will build your artifact, deploy it to Artifactory, and deploy it to the host specified in the DEPLOY_HOST parameter.

## Runing SonarQube Analysis and Dependency Check Report
Your pom.xml include the dependency check report plugin. See [documentation](https://confluence.vspglobal.com/x/LxZzBQ) on how to execute the report.
The project also include sonar.properties to run sonarqube code analysis and upload dependency report. Read [more](https://confluence.vspglobal.com/x/8x1bAg) about sonarQube configuration.

## Calling REST APIs
See the [Cloud Starter Reference API](https://git.vspglobal.com/projects/EA/repos/cloud-starter-reference-api/browse) for an example
1. See application.yaml for the configuration properties of the OAuth2RestTemplate.
2. See OAuth2RestTemplateConfig.java for how to configure the OAuth2RestTemplate bean.
3. See Oauth2RestTemplateController.java for how to use the OAuth2RestTemplate.
4. See RestTemplateResponseErrorHandler.java for how to add a custom error handler to the OAuth2RestTemplate.

## Running the Application

### NOTE:
- When running locally, set `-Dspring.profiles.active=LOCAL` to use the configuration properties defined under
the LOCAL profile in the `src/main/resources/application.yml`

NOTE:
- You must have Maven installed in order to work with this generated app. Please visit http://maven.apache.org/download.cgi and install Maven if you have not already done so.
- A small number of generated skeleton implementations may produce compilation warnings or errors about certain method parameters. You will have to import the app into Eclipse or IntelliJ IDEA and resolve them.
- You will need Java 8 to run the application.

Running from command line:

1. Open 'Terminal' or 'Command Prompt' and 'cd' into the directory where the zip file is extracted.

2. Run 'mvn clean install' to compile the app.

3. Run the app by entering the following command and replacing 'appname' with the name of your app:

- Windows: 'java -jar target\appname.jar --spring.cloud.consul.enabled=false'
- Mac OS X / Linux: 'java -jar target/appname.jar --spring.cloud.consul.enabled=false'

Importing into Eclipse:

1. Click on File->Import.

2. Choose Maven->Existing Maven Projects from the Import screen and click Next.

3. Click Browse and choose the root directory of your app from the Import Maven Projects screen and click Finish.

Running from Eclipse:

1. Right click on your app in the Package Explorer and click on Run As->Run Configurations.

2. Double click on Java Application and type a name for the configuration. Check the 'Include inherited mains when searching for a main class' checkbox and click Search. Search for and choose 'Application' from your package and click OK.

3. Click run to start the app.

Debugging from Eclipse:

1. Right click on your app in the Package Explorer and click on Debug As->Java Application and search for and choose 'Application' from your package and click OK.

Importing into IntelliJ IDEA:

1. From the Welcome screen, choose Import Project.

2. Browse and choose the root directory of your app form the Select File or Directory to Import screen.

3. From the Import Project screen, click on the 'Import project from external model' radio button and choose Maven and click Next. Choose the desired options on the following screen and click Next.

4. Leave the checkbox checked for 'Select profiles' and click Next. On the following screen, also leave the checkbox checked from 'Select Maven projects to import' and click next.

5. Select the project SDK by clicking on the green + button and choosing the path for JDK. Click Next and input the Project Name and click Finish.

Running from IntelliJ IDEA:

1. From the Run menu, choose Edit Configurations.

2. Click on the green + button and choose Application.

3. Type a name for the run configuration and click on the '...' button next to 'Main Class.' Search for and choose the 'Application' class from your package and click OK.

4. From the Run menu, choose Run and then choose the name of the run configuration you just created to start the app.

Debugging from IntelliJ IDEA:

1. From the Run menu, choose Debug and choose the name of the run configuration you created to run the app.

Initializing a local Git repository:

1. Open 'Terminal' or 'Command Prompt' and 'cd' into the directory where the app is located.

2. Run 'git init' to initialize a local git repository.

3. Specify in the .gitignore file in the root directory any files or directories you wish to not commit to your repository.
