package com.meddjamm.sn.config.motdepasse.service;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.motdepasse.TokenMotDePasse;
import com.meddjamm.sn.config.motdepasse.repository.TokenMotDePasseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenMotDePasseService {
    private final TokenMotDePasseRepository tokenMotDePasseRepository;

    public void createPasswordResetTokenForUser(Utilisateur utilisateur, String passwordToken) {
        TokenMotDePasse passwordRestToken = new TokenMotDePasse(passwordToken, utilisateur);
        tokenMotDePasseRepository.save(passwordRestToken);
    }

    public String validatePasswordResetToken(String passwordResetToken) {
        TokenMotDePasse passwordToken = tokenMotDePasseRepository.findByToken(passwordResetToken);
        if (passwordToken == null) {
            return "Invalide verification token";
        }
        Calendar calendar = Calendar.getInstance();
        if ((passwordToken.getTokenExpiration().getTime() - calendar.getTime().getTime()) <= 0) {
            return "Lien a déjà expiré, renvoyer à nouveau";
        }
        return "valide";
    }

    public Optional<Utilisateur> findUserByPasswordToken(String passwordResetToken) {
        return Optional.ofNullable(tokenMotDePasseRepository.findByToken(passwordResetToken).getUtilisateur());
    }

    public TokenMotDePasse findPasswordResetToken(String token) {
        return tokenMotDePasseRepository.findByToken(token);
    }
}