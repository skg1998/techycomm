package com.techycomm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.techycomm.api.config.SwaggerConfiguration;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class TechyCommApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechyCommApplication.class, args);
    }

}
