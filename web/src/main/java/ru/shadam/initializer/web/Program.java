package ru.shadam.initializer.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author sala
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Program {
    public static void main(String[] args) {
        SpringApplication.run(Program.class);
    }
}
