package com.bloodify.backend.services.SecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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

import com.bloodify.backend.config.RsaKeyProperties;
import com.bloodify.backend.dao.classes.InstitutionDAOImp;
import com.bloodify.backend.dao.classes.UserDAOImp;
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
        //TODO: Password Encoder
        return NoOpPasswordEncoder.getInstance();
    }

    // @Bean
    // abstract String getLoginEndpoint();

    // @Bean
    // abstract UserDetailsService userDetailsService();
    @Bean
    UserDetailsService userDetailsService(){
        return userDetailsService;
    }

    @Bean
    UserDetailsService insDetailsService(){
        return insDetailsService;
    }

    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {
    //     return (web) -> web.ignoring().requestMatchers("api/v1/instlogin", "api/v1/userlogin");
    // }


    // @Bean
    public AuthenticationManager userAuthenticationManager(HttpSecurity http) throws Exception {
         AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
         authenticationManagerBuilder
               .userDetailsService(userDetailsService)
               .passwordEncoder(passwordEncoder());
         return authenticationManagerBuilder.build();
     }

    //  @Bean
     public AuthenticationManager instAuthenticationManager(HttpSecurity http) throws Exception {
          AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
          authenticationManagerBuilder
                .userDetailsService(insDetailsService)
                .passwordEncoder(passwordEncoder());

          return authenticationManagerBuilder.build();
      }

    @Bean
    @Order(2)
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/api/v1/userlogin")

            .authorizeHttpRequests((auth) -> {
                // auth.requestMatchers("/api/v1/instlogin").permitAll();
                auth.requestMatchers("/api/v1/userlogin");
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/api/v1/instlogin")
            .authorizeHttpRequests((auth) -> {
                auth.requestMatchers("/api/v1/instlogin").permitAll();
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

    
}
