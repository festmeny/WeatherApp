package com.koncsikn.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.koncsikn"})
@PropertySource("classpath:application.properties")
public class AppConfig {
}
