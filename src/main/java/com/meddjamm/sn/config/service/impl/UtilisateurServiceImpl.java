package com.meddjamm.sn.config.service.impl;

import com.meddjamm.sn.config.entity.Profil;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.event.RegistrationCompleteEvent;
import com.meddjamm.sn.config.event.listener.RegistrationCompleteEventListener;
import com.meddjamm.sn.config.motdepasse.service.TokenMotDePasseService;
import com.meddjamm.sn.config.repository.ProfilRepository;
import com.meddjamm.sn.config.repository.UtilisateurRepository;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.exception.UtilisateurNotFoundException;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import static com.meddjamm.sn.utils.UtilString.generateCommonsLang3Password;
import static com.meddjamm.sn.utils.UtilString.genererMatricule;

@AllArgsConstructor
@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final ValidationService validationService;
    private final ApplicationEventPublisher publisher;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationCompleteEventListener eventListener;
    private final ProfilRepository profilRepository;

    @Override
    public Long saveUtilisateur(Utilisateur utilisateur, String url) throws Exception {
        String code = utilisateur.getCodeUtilisateur();
        Optional<Utilisateur> byCode = utilisateurRepository.findUtilisateurByCodeUtilisateur(code);
        if (utilisateur.getId() == null && byCode.isPresent()
                || (utilisateur.getId() != null && byCode.isPresent() && !byCode.get().getId().equals(utilisateur.getId()))) {
            throw new Exception(String.format("Le code %s est déjà associé à un compte utilisateur .", code));
        }
        String email = utilisateur.getEmail();
        Optional<Utilisateur> byEmail = utilisateurRepository.findUtilisateurByEmail(email);
        if (utilisateur.getId() == null && byEmail.isPresent()
                || (utilisateur.getId() != null && byEmail.isPresent() && !byEmail.get().getId().equals(utilisateur.getId()))) {
            throw new Exception(String.format("L'email %s est déjà associé à un compte utilisateur .", email));
        }
        String telephone = utilisateur.getTelephone();
        Optional<Utilisateur> byTelephone = utilisateurRepository.findUtilisateurByTelephone(telephone);
        if (utilisateur.getId() == null && byTelephone.isPresent()
                || (utilisateur.getId() != null && byTelephone.isPresent() && !byTelephone.get().getId().equals(utilisateur.getId()))) {
            throw new Exception(String.format("Le numéro de téléphone %s est déjà associé à un compte utilisateur .", telephone));
        }
        utilisateur.setMatricule(genererMatricule());
        String defaultPassword = generateCommonsLang3Password();
        utilisateur.setMotdepasse(passwordEncoder.encode(defaultPassword));
        utilisateur.setEst_valide(true);
        var savedUser = utilisateurRepository.saveAndFlush(utilisateur);
        savedUser.setMotdepasseprecedent(defaultPassword);
        publisher.publishEvent(new RegistrationCompleteEvent(savedUser, url));
        return savedUser.getId();
    }

    @Override
    public Utilisateur findUtilisateurById(Long utilisateurId) {
        if (utilisateurId == null) return null;
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurById(utilisateurId);
        Profil profil = profilRepository.findProfilById(utilisateur.getProfil().getId());
        utilisateur.setProfil(profil);
        return utilisateur;
    }

    @Override
    public Utilisateur findUserById(Long utilisateurId) {
        return utilisateurRepository.findUtilisateurById(utilisateurId);
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur findUtilisateurByEmail(String mail) {
        return utilisateurRepository.findByEmail(mail)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur inconnu"));
    }

    @Override
    public Utilisateur updateUserPass(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public String lireEnFonctionDuCode(String code) {
        var validation = validationService.lireEnFonctionDuCode(code);
        Utilisateur utilisateur = utilisateurRepository.findById(validation.getUtilisateur().getId())
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur inconnu"));
        utilisateur.setActif(true);
        return utilisateur.getEmail();
    }

    @Override
    public void changePassword(Utilisateur theUser, String newPassword) {
        theUser.setMotdepasse(passwordEncoder.encode(newPassword));
        utilisateurRepository.save(theUser);
    }

    @Override
    public boolean oldPasswordIsValid(Utilisateur user, String ancienMotDePasse) {
        return passwordEncoder.matches(ancienMotDePasse, user.getPassword());
    }

    @Override
    public void demandeResetMotDePasse(String email) {
        Utilisateur utilisateur = this.findUtilisateurByEmail(email);
        String newPassword = generateCommonsLang3Password();
        utilisateur.setMotdepasse(passwordEncoder.encode(newPassword));
        try {
            eventListener.sendPasswordResetVerificationEmail(utilisateur, newPassword);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Utilisateur findUtilisateurByMatricule(String matricule) {
        return utilisateurRepository.findUtilisateurByMatricule(matricule);
    }

    @Override
    public void deleteUtilisateur(String email) {
        var utilisateur = this.findUtilisateurByEmail(email);
        utilisateur.setEst_valide(false);
    }

    @Override
    public List<Utilisateur> findAllMedecins() {
        return utilisateurRepository.findAllMedecins();
    }

    @Override
    public void activatedAccount(String matricule) {
        var utilisateur = this.findUtilisateurByMatricule(matricule);
        utilisateur.setActif(true);
    }

    @Override
    public void deactivatedAccount(String matricule) {
        var utilisateur = this.findUtilisateurByMatricule(matricule);
        utilisateur.setActif(false);
    }
}
