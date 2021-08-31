package com.example.algamoney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AlgamoneyApiApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext run = SpringApplication.run(AlgamoneyApiApplication.class, args);
    }

}
