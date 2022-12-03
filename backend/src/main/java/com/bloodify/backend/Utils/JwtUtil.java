//package com.bloodify.backend.Utils;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//
//@Service
//public class JwtUtil {
//    private final String SECRET_KEY = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    protected Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//    }
//
//    protected Boolean isTokenExpired(String token){
//        return false;
//    }
//
//    public String generateToken(UserDetails userDetails, Date now) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, userDetails.getUsername(), now);
//    }
//
//    protected String createToken(Map<String, Object> claims, String subject, Date now) {
//        return Jwts.builder().setClaims(claims)
//                                .setSubject(subject)
//                                .setIssuedAt(now)
//                                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                                .compact();
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        String username = extractUsername(token);
//        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//    }
//
//
//}
