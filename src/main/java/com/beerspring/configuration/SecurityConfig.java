package com.beerspring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * Created by markryan on 2/16/15.
 */
@Configuration
@EnableWebMvcSecurity
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         *
         * THE CSRF DISABLING MIGHT BE BAD HERE!!!
         */
        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin().loginPage("/login").permitAll().and().logout()
                .permitAll();

        http.headers().frameOptions().disable(); //for H2 web console
    }
}
