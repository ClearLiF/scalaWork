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
        System.out.println("clear修改");
       // System.out.println("Hello World!");
        System.out.println("master测试");
        SpringApplication.run(App.class, args);

    }
}
