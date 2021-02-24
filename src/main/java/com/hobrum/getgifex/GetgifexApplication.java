package com.hobrum.getgifex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.hobrum.getgifex.controller",
                                    "com.hobrum.getgifex.client",
                                    "com.hobrum.getgifex.service"})
public class GetgifexApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetgifexApplication.class, args);
    }

}
