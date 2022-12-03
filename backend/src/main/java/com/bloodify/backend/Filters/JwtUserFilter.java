package com.bloodify.backend.Filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bloodify.backend.Utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUserFilter extends OncePerRequestFilter {

    @Lazy
    @Autowired
    UserDetailsService bloodifyUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{

            log.debug("Entering jwt filter");

            String auth = request.getHeader("Authorization");
            log.debug("Authorization header: " + auth);

            String jwt = null, username = null;

            if(auth != null && auth.startsWith("Bearer ")){
                log.debug("Jwt authoriztation is used");

                jwt = auth.substring(7);

                username = jwtUtil.extractUsername(jwt);
                log.debug("username is: " + username);
            }

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = this.bloodifyUserDetailsService.loadUserByUsername(username);

                log.debug("Validation jwt token");
                if(jwtUtil.validateToken(jwt, userDetails)) {
                    log.debug("token is valid");
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
                                                        = new UsernamePasswordAuthenticationToken
                                                        (   userDetails,
                                                            null,
                                                            userDetails.getAuthorities()
                                                        );
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            log.debug("Exiting jwt filter");

            filterChain.doFilter(request, response);
        }catch(Exception e){
            logger.info(e.getCause());
        }
    }
    
}
