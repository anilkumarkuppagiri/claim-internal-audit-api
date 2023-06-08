package com.vsp.api.claiminternalauditapi;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * An configuration class is required to inject beans into the application context
 * used during JUnit execution. A RestTemplateBuilder bean must be included in
 * the class. Other Spring beans can be declared here as well.
 */
@Configuration
public class TestConfiguration {

    // Required, do not remove
    @Bean
    public RestTemplateBuilder getRestTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    // Define other beans here

}
