package com.vsp.api.claiminternalauditapi;

import com.vsp.il.util.Preferences;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class Application {

    public static void main(String[] args) {
        /**
        * Note: The Preferences are only initialized for building links.
        * DO NOT use VSP Preferences for anything else. Use Spring Configuration
        * Properties. See application.yaml
        */
        Preferences.initialize();

        SpringApplication.run(Application.class, args);
    }

}
