package com.chabane.categoryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CategoryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategoryServerApplication.class, args);
    }

}
