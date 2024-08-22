package com.spring.security.services;

import com.spring.security.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
    }

  public String generateToken(User user){
    return  Jwts.builder()
              .subject(String.valueOf(user.getId()))
              .claim("email", user.getEmail())
              .claim("roles", Set.of("USER", "ADMIN"))
              .issuedAt(new Date())
              .expiration(new Date(System.currentTimeMillis() + 1000 * 60))
              .signWith(getSecretKey())
              .compact();
  }

  public Long getUserIdFromToken(String token){
      Claims claims =  Jwts.parser()
              .verifyWith(getSecretKey())
              .build()
              .parseSignedClaims(token)
              .getPayload();

      return Long.parseLong(claims.getSubject());
  }

}
