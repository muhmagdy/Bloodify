package com.bloodify.backend.AccountManagement.Utils;

import java.security.Principal;
import java.time.Instant;
// import java.time.temporal.ChronoUnit;
// import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenUtil {

    @Autowired
    private JwtEncoder encoder;

    public String generateToken(Principal authentication) {
        Instant now = Instant.now();
        // String scope = authentication.getAuthorities().stream()
        //         .map(GrantedAuthority::getAuthority)
        //         .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                // .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                // .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
