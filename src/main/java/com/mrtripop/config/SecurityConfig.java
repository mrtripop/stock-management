package com.mrtripop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails john =
//                User.builder().username("john").password("{noop}test123").roles("EMPLOYEE").build();
//        UserDetails mary =
//                User.builder()
//                        .username("mary")
//                        .password("{noop}test123")
//                        .roles("EMPLOYEE", "MANAGER")
//                        .build();
//        UserDetails susan =
//                User.builder()
//                        .username("susan")
//                        .password("{noop}test123")
//                        .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                        .build();
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                configuer ->
                        configuer
                                .requestMatchers(HttpMethod.GET, "/api/inventory/products").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/inventory/products/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/api/inventory/products").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT, "/api/inventory/products/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE, "/api/inventory/products/**").hasRole("ADMIN")
        );

        // use HTTP basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable CSRF
        // in general, not require for stateless REST APIs
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
