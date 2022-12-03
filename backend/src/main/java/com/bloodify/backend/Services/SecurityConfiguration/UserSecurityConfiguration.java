package com.bloodify.backend.services.SecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bloodify.backend.Filters.JwtUserFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class UserSecurityConfiguration extends SecurityConfiguration {

    // @Autowired
    // JwtUserFilter jwtUserFilter;

    @Autowired
    UserDetailsService bloodifyUserDetailsService;

    @Override
    UserDetailsService userDetailsService() {
        return bloodifyUserDetailsService;
    }

    @Override
    String getLoginEndpoint() {
        return "/api/v1/userlogin";
    }

    // @Override
    // OncePerRequestFilter getJwtFilter() {
    //     return jwtUserFilter;
    // }
    
}
