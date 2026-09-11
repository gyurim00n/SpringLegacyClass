package org.doit.ik.di5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.doit.ik.di5")
public class Config5 {

    @Bean
    public User user1() {
        return new User("bkchoi", "1234");
    }

    @Bean
    public User user2() {
        return new User("madvirous", "qwsr");
    }
}