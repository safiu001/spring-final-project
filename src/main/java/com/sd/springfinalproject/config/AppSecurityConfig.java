package com.sd.springfinalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.security.Security;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/app/home", "/app/movieList", "/app/myList", "/app/addToMyList", "/app/deleteFromMyList").hasRole("USER")
//                .antMatchers("/app/**").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/customLoginForm")
//                .loginProcessingUrl("/authenticateTheUser")
//                .successForwardUrl("/app/successLogin")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//                .permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/accessDenied");
//        return http.build();
//    }
}
