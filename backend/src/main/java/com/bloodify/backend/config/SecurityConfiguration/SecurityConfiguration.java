package com.bloodify.backend.config.SecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bloodify.backend.config.RsaKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    @Qualifier("userDAOImp")
    UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("institutionDAOImp")
    UserDetailsService insDetailsService;

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
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    UserDetailsService userDetailsService(){
        return userDetailsService;
    }

    @Bean
    UserDetailsService instDetailsService(){
        return insDetailsService;
    }

    private AuthenticationManager userAuthenticationManager(HttpSecurity http) throws Exception {
         AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
         authenticationManagerBuilder
               .userDetailsService(userDetailsService())
               .passwordEncoder(passwordEncoder());
         return authenticationManagerBuilder.build();
     }

     private AuthenticationManager instAuthenticationManager(HttpSecurity http) throws Exception {
          AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
          authenticationManagerBuilder
                .userDetailsService(instDetailsService())
                .passwordEncoder(passwordEncoder());

          return authenticationManagerBuilder.build();
    }


    private final String endpoint = "/api/v1";

    @Bean
    @Order(2)
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
        String endpoint =  this.endpoint + "/user";
        http
            .securityMatcher(endpoint + "/**")
            .authorizeHttpRequests((auth) -> {
                auth.requestMatchers(endpoint + "/auth").permitAll();
                auth.anyRequest().authenticated();
            }

            )
            .csrf().disable()
            .httpBasic()
            .and()
            .authenticationManager(this.userAuthenticationManager(http))
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain instFilterChain(HttpSecurity http) throws Exception {
        String endpoint =  this.endpoint + "/institution";
        http
            .securityMatcher(endpoint + "/**")
            .authorizeHttpRequests((auth) -> {
                auth.requestMatchers(endpoint + "/auth").permitAll();
                auth.anyRequest().authenticated();
            }

            )
            .csrf().disable()
            .httpBasic()
            .and()
            .authenticationManager(this.instAuthenticationManager(http))
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                this.endpoint + "/test/**",
                this.endpoint+"/user",
                this.endpoint+"/institution");
    }

}
