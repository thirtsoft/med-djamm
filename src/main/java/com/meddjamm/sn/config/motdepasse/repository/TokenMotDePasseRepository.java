package com.meddjamm.sn.config.motdepasse.repository;

import com.meddjamm.sn.config.motdepasse.TokenMotDePasse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenMotDePasseRepository extends JpaRepository<TokenMotDePasse, Long> {
    TokenMotDePasse findByToken(String passwordResetToken);
}