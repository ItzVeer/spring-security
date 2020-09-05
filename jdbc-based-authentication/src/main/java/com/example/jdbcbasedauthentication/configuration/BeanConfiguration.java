package com.example.jdbcbasedauthentication.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class BeanConfiguration {

    @Bean
    UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

    @Bean
    InitializingBean initializingBean(UserDetailsManager manager) {
        return () -> {
            UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("user").build();
            manager.createUser(admin);

            UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user").roles("user").build();
            manager.createUser(user);
        };
    }

}
