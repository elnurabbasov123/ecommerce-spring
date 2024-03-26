package com.example.ecommerse.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.ECGenParameterSpec;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static String SECRET_KEY = "srthealik1234srthealik1234srthealik1234srthealik1234srthealik1234";


    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public List<SimpleGrantedAuthority> extractAuthorities(String jwt) {
        List<String> authorities = (List<String>) extractAllClaims(jwt).get("authorities");

        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolve) {
        Claims claims = extractAllClaims(token);

        return claimResolve.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String jwt) {
        return isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpression(jwt).after(new Date());
    }

    private Date extractExpression(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    public String generateToken(String email, Map<String, Object> extraClaims) throws Exception {
        return generateToken(extraClaims, email);
    }

    private String generateToken(Map<String, Object> map, String email) throws Exception {

        return Jwts.
                builder()
                .addClaims(map)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 180000))
                .signWith(getSecretKey())
                .compact();
    }

    private Key getSecretKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA512");
    }
}
