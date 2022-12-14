//package com.bloodify.backend.Utils;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.Date;
//import java.util.HashMap;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//public class JwtUtilTest {
//
//    static JwtUtil jwtUtil;
//
//    @BeforeAll
//    static void init(){
//        jwtUtil = new JwtUtil();
//    }
//
//    @Test
//    void testCreateToken() {
//        Date time = new Date(1234658);
//        String actualToken = jwtUtil.createToken(new HashMap<>(), "John Doe", time);
//        String expectedToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2huIERvZSIsImlhdCI6MTIzNH0.PgVA_7d08wppfVFn_O8ejr95JdIABA74-H7zyTMywSI";
//        assertEquals(jwtUtil.extractAllClaims(expectedToken), actualToken);
//    }
//
//    @Test
//    void testExtractAllClaims() {
//        // String token = ""
//
//    }
//
//    @Test
//    void testExtractClaim() {
//
//    }
//
//    @Test
//    void testExtractExpiration() {
//
//    }
//
//    @Test
//    void testExtractUsername() {
//
//    }
//
//    @Test
//    void testGenerateToken() {
//
//    }
//
//    @Test
//    void testIsTokenExpired() {
//
//    }
//
//    @Test
//    void testValidateToken() {
//
//    }
//}
