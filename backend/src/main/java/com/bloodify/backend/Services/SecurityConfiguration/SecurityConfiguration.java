package com.bloodify.backend.services.SecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bloodify.backend.config.RsaKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


abstract public class SecurityConfiguration {


    @Autowired
    private RsaKeyProperties rsaKeys;

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //TODO: Password Encoder
        return NoOpPasswordEncoder.getInstance();
    }

    // @Bean
    // abstract OncePerRequestFilter getJwtFilter();

    @Bean
    abstract String getLoginEndpoint();

    @Bean
    abstract UserDetailsService userDetailsService();

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
         AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
         authenticationManagerBuilder
               .userDetailsService(userDetailsService())
               .passwordEncoder(passwordEncoder());
         return authenticationManagerBuilder.build();
     }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers(getLoginEndpoint()).permitAll()
                .anyRequest().authenticated()
            )
            .csrf().disable()
            .httpBasic()
            .and()
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // http.addFilterBefore(getJwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    
}
