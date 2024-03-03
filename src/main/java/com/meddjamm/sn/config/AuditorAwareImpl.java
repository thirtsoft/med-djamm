package com.meddjamm.sn.config;

import com.meddjamm.sn.config.entity.Utilisateur;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return getCurrentUser();
    }

    private Optional<String> getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return of("root");
        }
        Utilisateur userPrincipal = (Utilisateur) authentication.getPrincipal();
        return ofNullable(userPrincipal.getUsername());
    }
}