package com.teamknp.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Component
public class ThymeleafDialectConfig {
    @Bean
    Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    @Bean
    SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}
