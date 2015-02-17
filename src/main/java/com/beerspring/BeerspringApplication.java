package com.beerspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class BeerspringApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(BeerspringApplication.class, args);


    }

}
