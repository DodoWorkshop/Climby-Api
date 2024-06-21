package com.dodoworkshop.climbyapi.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@UtilityClass
public class UserUtils {

    public JwtAuthenticationToken getAuthentication() {
        final var auth = SecurityContextHolder.getContext()
                .getAuthentication();

        if (auth instanceof JwtAuthenticationToken jwtAuthentication) return jwtAuthentication;

        throw new RuntimeException("No handled authentication found");
    }

    public String getUserId() {
        return getAuthentication().getName();
    }
}
