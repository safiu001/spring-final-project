package com.sd.springfinalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig{
    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcUserDetailsManager userDetailsService(){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/app/home", "/app/movieList", "/app/myList", "/app/addToMyList", "/app/deleteFromMyList").hasRole("USER")
                .antMatchers("/app/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/customLoginForm")
                .loginProcessingUrl("/authenticateTheUser")
                .successForwardUrl("/app/successLogin")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
        return http.build();
    }
}
