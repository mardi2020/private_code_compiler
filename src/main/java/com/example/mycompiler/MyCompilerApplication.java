package com.example.mycompiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MyCompilerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyCompilerApplication.class, args);
    }

}
