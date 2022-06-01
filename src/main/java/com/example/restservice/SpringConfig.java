package com.example.restservice;

import com.example.restservice.Cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean("cache")
    Cache cache(){
        return new Cache();
    }
}
