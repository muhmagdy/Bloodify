// package com.bloodify.backend.services.SecurityConfiguration;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.annotation.Order;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
// import org.springframework.security.core.userdetails.UserDetailsService;

// @Configuration
// @EnableWebSecurity
// @Order(2)
// public class InstitutionSecurityConfiguration extends SecurityConfiguration {

//     @Autowired
//     @Qualifier("institutionDAOImp")
//     UserDetailsService institutionDao;

//     @Override
//     String getLoginEndpoint() {
//         return "api/v1/instlogin";
//     }

//     @Override
//     UserDetailsService userDetailsService() {
//         return institutionDao;
//     }
    
// }
