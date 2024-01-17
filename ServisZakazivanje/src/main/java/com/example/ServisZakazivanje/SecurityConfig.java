//package com.example.ServisZakazivanje;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurationAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/api/training-appointments/**").authenticated()
//                // Add other configurations as needed
//                .and()
//                .oauth2ResourceServer()
//                .jwt(); // Configure JWT authentication
//    }
//
//    // Other security-related configurations, if needed
//}
