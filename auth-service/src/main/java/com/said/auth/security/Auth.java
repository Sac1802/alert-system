package com.said.auth.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Auth {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration-minutes}")
    private int expirationMinutes;

    @Value("${jwt.refresh-expiration-days}")
    private int refreshExpirationDays;

    public String createToken(int id, String email) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withSubject(String.valueOf(id))
                .withClaim("email", email)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES))
                .sign(algorithm);
    }

    public String createRefreshToken(int userId){
        return JWT.create()
        .withSubject(String.valueOf(userId))
        .withIssuedAt(Instant.now())
        .withExpiresAt(Instant.now().plus(refreshExpirationDays, ChronoUnit.DAYS))
        .sign(Algorithm.HMAC256(secretKey));
    }

    public DecodedJWT validateToken(String token){
        return JWT.require(Algorithm.HMAC256(secretKey))
        .build()
        .verify(token);
    }

    public long getId(String token){
        return Long.parseLong(validateToken(token).getSubject());
    }
}
