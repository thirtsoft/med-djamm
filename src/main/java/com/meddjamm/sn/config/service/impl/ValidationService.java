package com.meddjamm.sn.config.service.impl;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.entity.Validation;
import com.meddjamm.sn.config.repository.ValidationRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.MINUTES;

@AllArgsConstructor
@Service
public class ValidationService {

    private ValidationRepository validationRepository;
    private NotificationService notificationService;

    public void enregistrer(Utilisateur utilisateur, String url) throws MessagingException {
        Validation validation = new Validation();
        validation.setUtilisateur(utilisateur);
        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10, MINUTES);
        validation.setExpiration(expiration);

        validation.setCode(UUID.randomUUID().toString());
        this.validationRepository.save(validation);
        this.notificationService.envoyerEmailDeValidation(validation, url);
    }


    public void enregistrerCode(Utilisateur utilisateur, String token) {
        Validation validation = new Validation();
        validation.setUtilisateur(utilisateur);
        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10, MINUTES);
        validation.setExpiration(expiration);
        validation.setCode(token);

        this.validationRepository.save(validation);
    }

    public Validation lireEnFonctionDuCode(String code) {
        return this.validationRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Votre code est invalide"));
    }
}