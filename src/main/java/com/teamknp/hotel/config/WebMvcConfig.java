package com.teamknp.hotel.config;

import io.springlets.format.config.SpringletsEntityFormatWebConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends SpringletsEntityFormatWebConfiguration {
    public WebMvcConfig(MessageSource messageSource, ApplicationContext applicationContext) {
        super(messageSource, applicationContext);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").
                addResourceLocations("classpath:/static/");
    }
}
