package com.meddjamm.sn.config.service.impl;

import com.meddjamm.sn.config.entity.Profil;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.event.RegistrationCompleteEvent;
import com.meddjamm.sn.config.event.listener.RegistrationCompleteEventListener;
import com.meddjamm.sn.config.motdepasse.service.TokenMotDePasseService;
import com.meddjamm.sn.config.repository.ProfilRepository;
import com.meddjamm.sn.config.repository.UtilisateurrRepository;
import com.meddjamm.sn.config.service.UtilisateurService;
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
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.meddjamm.sn.utils.UtilString.generateCommonsLang3Password;
import static com.meddjamm.sn.utils.UtilString.genererMatricule;
import static java.util.Collections.emptyList;

@AllArgsConstructor
@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurrRepository utilisateurrRepository;
    private final ValidationService validationService;
    private final ApplicationEventPublisher publisher;
    private final TokenMotDePasseService tokenMotDePasseService;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationCompleteEventListener eventListener;

    private final ProfilRepository profilRepository;

    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur, String url) {
        utilisateur.setMatricule(genererMatricule());
        String defaultPassword = generateCommonsLang3Password();
        utilisateur.setMotdepasse(passwordEncoder.encode(defaultPassword));
        var savedUser = utilisateurrRepository.saveAndFlush(utilisateur);
        savedUser.setMotdepasseprecedent(defaultPassword);
        publisher.publishEvent(new RegistrationCompleteEvent(savedUser, url));
        return savedUser;
    }

    @Override
    public Utilisateur findUtilisateurByCode(String code) throws Exception {
        Assert.notNull(code, "Ne doit pas etre null");
        if (code == null && "".equals(code)) return null;
        return utilisateurrRepository.findByCodeUtilisateur(code);
    }

    @Override
    public Utilisateur findUtilisateurById(Long utilisateurId) {
        if (utilisateurId == null) return null;
        Utilisateur utilisateur = utilisateurrRepository.findUtilisateurById(utilisateurId);
        Profil profil = profilRepository.findProfilById(utilisateur.getProfil().getId());
        utilisateur.setProfil(profil);
        return utilisateur;
    }

    @Override
    public Utilisateur findUserById(Long utilisateurId) {
        return utilisateurrRepository.findUtilisateurById(utilisateurId);
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return utilisateurrRepository.findAll();
    }

    @Override
    public List<Utilisateur> findAllActives() {
        return utilisateurrRepository.findAllActive();
    }

    @Override
    public boolean deleteUtilisateur(Utilisateur utilisateur) throws Exception {
        return false;
    }

    @Override
    public boolean checkValiditePass(String mdp) throws Exception {
        if (mdp.length() < 8)
            throw new IllegalArgumentException("Le mot de passe doit comporter au moins 8 caractères");
        boolean maj = false, chif = false;
        for (int i = 0; i < mdp.length(); i++) {
            Character c = mdp.charAt(i);
            if (Character.isDigit(c)) chif = true;
            if (Character.isUpperCase(c)) maj = true;
        }
        if (!chif) throw new Exception("Le mot de passe doit comporter au moins un chiffre");
        if (!maj) throw new Exception("Le mot de passe doit comporter au moins une majuscule");
        return true;
    }

    @Override
    public Utilisateur findUtilisateurByEmail(String mail) {
        return utilisateurrRepository.findByEmail(mail)
                .orElseThrow();
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
        byte[] b = cle.getEncoded();
        return b;
    }

    @Override
    public void updateUserPass(Utilisateur utilisateur) throws Exception {
        utilisateurrRepository.save(utilisateur);
        //   utilsRepositoryCustom.sendMailUpdatePass(utilisateur);
    }

    @Override
    public void resetUserPass(Utilisateur utilisateur) throws Exception {
        utilisateurrRepository.save(utilisateur);
        //   utilsRepositoryCustom.sendMailForgotPass(utilisateur, utilisateurDTO.getNewPass());
    }

    @Override
    public String findNomComplet(Long id) {
        Utilisateur utilisateur = utilisateurrRepository.findUtilisateurById(id);
        if (utilisateur == null) return "";
        return utilisateur.getPrenom() + " " + utilisateur.getNom();
    }

    @Override
    public void lireEnFonctionDuCode(String code) {
        var validation = validationService.lireEnFonctionDuCode(code);
        if (Instant.now().isAfter(validation.getExpiration())) {
            return;
        }
        Utilisateur utilisateur = utilisateurrRepository.findById(validation.getUtilisateur().getId()).orElseThrow(() -> new RuntimeException("Utilisateur inconnu"));
        utilisateur.setActif(true);
    }

    @Override
    public void regenererMotDePassePourUtilisateur() {

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
        utilisateurrRepository.save(theUser);
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
            return passwordResetEmailLink(url.concat(token));
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Utilisateur findUtilisateurByMatricule(String matricule) {
        return utilisateurrRepository.findUtilisateurByMatricule(matricule);
    }

    private String passwordResetEmailLink(String applicationUrl) throws MessagingException, UnsupportedEncodingException {
        eventListener.sendPasswordResetVerificationEmail(applicationUrl);
        log.info("Click the link to reset your password :  {}", applicationUrl);
        return applicationUrl;
    }
}
