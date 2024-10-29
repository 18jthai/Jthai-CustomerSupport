package com.example.jthaicustomersupport.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.Controller;

@Configuration
@ComponentScan(basePackages = "com.example.jthaicustomersupport.site",
        excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootContextConfig {
}
