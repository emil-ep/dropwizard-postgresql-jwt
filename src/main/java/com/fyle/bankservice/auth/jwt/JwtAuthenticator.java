package com.fyle.bankservice.auth.jwt;

import io.dropwizard.auth.Authenticator;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


public class JwtAuthenticator implements Authenticator<JwtContext, User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticator.class);


    @Override
    public Optional<User> authenticate(JwtContext context) {
        try {
            JwtClaims claims = context.getJwtClaims();

            int id = Integer.parseInt(claims.getSubject());
            String username = (String) claims.getClaimValue("user");
            String roles = (String) claims.getClaimValue("roles");

            return Optional.of(new User(id, username, roles));
        } catch (Exception e) {
            LOGGER.warn("msg=Failed to authorise user: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }
}