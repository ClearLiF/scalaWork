package com.cuit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 */
@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.cuit"})
@MapperScan(basePackages = {"com.cuit", "generator"})
public class App {
    public static void main(String[] args) {
       // System.out.println("Hello World!");
        SpringApplication.run(App.class, args);

    }
}
