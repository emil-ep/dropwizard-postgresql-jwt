package com.fyle.bankservice.auth.jwt;

import io.dropwizard.auth.Authorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JwtAuthoriser implements Authorizer<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthoriser.class);

    @Override
    public boolean authorize(User user, String requiredRole) {
        if (user == null) {
            LOGGER.warn("msg=user object was null");
            return false;
        }

        String roles = user.getRoles();
        if (roles == null) {
            LOGGER.warn("msg=roles were null, user={}, userId={}", user.getName(), user.getId());
            return false;
        }
        return roles.contains(requiredRole);
    }
}