package com.example.jthaicustomersupport.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.Controller;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.jthaicustomersupport.site",
                useDefaultFilters = false,
                includeFilters = @ComponentScan.Filter(Controller.class))
public class ServletContextConfig {
}
