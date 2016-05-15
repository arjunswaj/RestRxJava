package com.asb.rxjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

@Configuration
@SpringBootApplication
@ComponentScan("com.asb.rxjava")
public class RestRxJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestRxJavaApplication.class, args);
    }

    @Bean
    public ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean() {
        return new ThreadPoolExecutorFactoryBean();
    }
}
