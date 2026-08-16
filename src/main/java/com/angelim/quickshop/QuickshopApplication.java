package com.angelim.quickshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class QuickshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickshopApplication.class, args);
    }

}
