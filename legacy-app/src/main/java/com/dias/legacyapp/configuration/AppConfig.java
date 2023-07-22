package com.dias.legacyapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Random;
import java.util.function.Function;

@EnableScheduling
@Configuration
public class AppConfig {

    @Bean
    public Random random() {
        return new Random();
    }

}
