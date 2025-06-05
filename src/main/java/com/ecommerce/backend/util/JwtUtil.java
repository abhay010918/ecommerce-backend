package com.ecommerce.backend.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ecommerce.backend.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.algorithm.key}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private String expiryDuration;

    private Algorithm algorithm;

    private long expiryMs;

    @PostConstruct
    public void PostConstruct(){
        algorithm = Algorithm.HMAC256((secret));
        expiryMs = Long.parseLong(expiryDuration);
    }

    public String generateToken(User user){
        return JWT.create()
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole().name())
                .withExpiresAt( new Date(System.currentTimeMillis() + expiryMs))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUserEmail(String token){
        DecodedJWT verify = JWT.require(algorithm)
                .withIssuer(issuer)
                .build().verify(token);

        return verify.getClaim("email").asString();
    }

    public String getRole(String token){
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
        return decodedJWT.getClaim("role").asString();
    }

}
