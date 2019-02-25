package com.example.sell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@SpringBootApplication
@EnableSwagger2
public class SellApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellApplication.class, args);
		//12536
		//123456
    }
}
