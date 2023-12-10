package com.sowmik.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthenticationSecurityConfiguration {
    //Filter chain
    //authenticate all requests
    //basic authentication
    //disabling csrf
    //stateless rest api
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //1: Response to preflight request doesn't pass access control check
        //2: basic auth
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
        );
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }
}
