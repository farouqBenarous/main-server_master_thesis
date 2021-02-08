package com.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
 /*       http.anonymous().disable()
            .requestMatchers().antMatchers("/v1/**")
            .and().authorizeRequests()
            .antMatchers("/v1/**").access("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_CLIENT_ADMIN')  or hasRole('ROLE_GLOBAL_SALES_MANAGER')  or hasRole('ROLE_REGIONAL_MANAGER')  or hasRole('ROLE_SALES_EXECUTIVE')")
            .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());*/
        //http.authenticateRequest().antMatcher("/**").permitAll();
        http.authorizeRequests().antMatchers("/**").permitAll();

    }
}