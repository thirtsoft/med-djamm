package com.meddjamm.sn.config.security;

public final class URL {

    private URL() {
        // Not Implemented
    }

    static final String[] WHITE_LIST_URL = {
            "/h2-console/**",
            "/api/v1/auth/authenticate",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };
}