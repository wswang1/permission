package com.niii.permission.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests开端--表示开始说明需要的权限
        //需要的权限分两部分 1、拦截的路径 2、访问改路径需要的权限
        //.antMatchers("/**").permitAll() --> 所有路径，允许所有权限
        // .anyRequest().authenticated() --> 任何的请求，需要在authenticated认证后才能访问

        http
                .authorizeRequests()
                .antMatchers("/**").permitAll().antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

    }
}
