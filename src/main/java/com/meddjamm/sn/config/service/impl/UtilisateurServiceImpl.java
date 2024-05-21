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
import org.springframework.util.Assert;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.meddjamm.sn.utils.UtilString.generateCommonsLang3Password;
import static com.meddjamm.sn.utils.UtilString.genererMatricule;
import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;
import static java.util.Collections.emptyList;

@AllArgsConstructor
@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final ValidationService validationService;
    private final ApplicationEventPublisher publisher;
    private final TokenMotDePasseService tokenMotDePasseService;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationCompleteEventListener eventListener;

    private final ProfilRepository profilRepository;

    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur, String url) throws Exception {
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
            throw new Exception(String.format("Le numéro de téléphone %s est déjà associé à un compte utilisateur .", email));
        }
        utilisateur.setMatricule(genererMatricule());
        String defaultPassword = generateCommonsLang3Password();
        utilisateur.setMotdepasse(passwordEncoder.encode(defaultPassword));
        var savedUser = utilisateurRepository.saveAndFlush(utilisateur);
        savedUser.setMotdepasseprecedent(defaultPassword);
        publisher.publishEvent(new RegistrationCompleteEvent(savedUser, url));
        return savedUser;
    }

    @Override
    public Utilisateur findUtilisateurByCode(String code) {
        Assert.notNull(code, "Ne doit pas etre null");
        if (code == null && "".equals(code)) return null;
        return utilisateurRepository.findByCodeUtilisateur(code);
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
    public List<Utilisateur> findAllActives() {
        return utilisateurRepository.findAllActive();
    }

    @Override
    public boolean checkValiditePass(String mdp) {
        if (mdp.length() < 8)
            throw new IllegalArgumentException("Le mot de passe doit comporter au moins 8 caractères");
        boolean maj = false;
        boolean chif = false;
        for (int i = 0; i < mdp.length(); i++) {
            char c = mdp.charAt(i);
            if (isDigit(c)) chif = true;
            if (isUpperCase(c)) maj = true;
        }
        if (!chif) throw new IllegalArgumentException("Le mot de passe doit comporter au moins un chiffre");
        if (!maj) throw new IllegalArgumentException("Le mot de passe doit comporter au moins une majuscule");
        return true;
    }

    @Override
    public Utilisateur findUtilisateurByEmail(String mail) {
        return utilisateurRepository.findByEmail(mail)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur inconnu"));
    }

    @Override
    public List<Utilisateur> getListeUsers(List<Utilisateur> userDTOs, int page, int ligneParPage) {
        return emptyList();
    }

    @Override
    public byte[] generateNewKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen;
        keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey cle = keyGen.generateKey();
        return cle.getEncoded();
    }

    @Override
    public Utilisateur updateUserPass(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public void resetUserPass(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public String findNomComplet(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurById(id);
        if (utilisateur == null) return "";
        return utilisateur.getPrenom() + " " + utilisateur.getNom();
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
    public String validatePasswordResetToken(String token) {
        return tokenMotDePasseService.validatePasswordResetToken(token);
    }

    @Override
    public Utilisateur findUserByPasswordToken(String token) {
        return tokenMotDePasseService.findUserByPasswordToken(token).orElseThrow();
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
    public void createPasswordResetTokenForUser(Utilisateur user, String passwordResetToken) {
        tokenMotDePasseService.createPasswordResetTokenForUser(user, passwordResetToken);
    }

    @Override
    public String demandeChangerMotDePasse(String email, String url) {
        Utilisateur utilisateur = this.findUtilisateurByEmail(email);
        String token = UUID.randomUUID().toString();
        this.createPasswordResetTokenForUser(utilisateur, token);
        try {
            return passwordResetEmailLink(utilisateur, url.concat(token));
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
        utilisateur.setActif(false);
    }

    @Override
    public List<Utilisateur> findAllMedecins() {
        return utilisateurRepository.findAllMedecins();
    }

    private String passwordResetEmailLink(Utilisateur utilisateur, String applicationUrl) throws MessagingException, UnsupportedEncodingException {
        eventListener.sendPasswordResetVerificationEmail(utilisateur, applicationUrl);
        log.info("Click the link to reset your password :  {}", applicationUrl);
        return applicationUrl;
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
