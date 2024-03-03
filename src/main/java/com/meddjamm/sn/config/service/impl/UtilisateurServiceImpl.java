package com.meddjamm.sn.config.service.impl;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.repository.UtilisateurrRepository;
import com.meddjamm.sn.config.service.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurrRepository utilisateurrRepository;

    private final ValidationService validationService;

    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) throws Exception {
        if (utilisateur == null) {
            throw new RuntimeException("L'objet à sauvegarder est nul");
        }
        var savedUser = utilisateurrRepository.saveAndFlush(utilisateur);
        validationService.enregistrer(savedUser);
        return savedUser;
    }

    @Override
    public Utilisateur findUtilisateurByCode(String code) throws Exception {
        if (code == null && "".equals(code))
            return null;
        return utilisateurrRepository.findByCodeUtilisateur(code);
    }

    @Override
    public Utilisateur findUtilisateurById(Long utilisateurId) throws Exception {
        if (utilisateurId == null)
            return null;
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
    public boolean deleteUtilisateur(Utilisateur Utilisateur) throws Exception {
        return false;
    }

    @Override
    public boolean checkValiditePass(String mdp) throws Exception {
        if (mdp.length() < 8)
            throw new Exception("Le mot de passe doit comporter au moins 8 caractères");
        boolean maj = false, chif = false;
        for (int i = 0; i < mdp.length(); i++) {
            Character c = mdp.charAt(i);
            if (Character.isDigit(c))
                chif = true;
            if (Character.isUpperCase(c))
                maj = true;
        }
        if (!chif)
            throw new Exception("Le mot de passe doit comporter au moins un chiffre");
        if (!maj)
            throw new Exception("Le mot de passe doit comporter au moins une majuscule");
        return true;
    }

    @Override
    public Utilisateur findUtilisateurByEmail(String mail) throws Exception {
        if (mail != null && "".equals(mail))
            return null;
        return utilisateurrRepository.findByMail(mail);
    }

    @Override
    public List<Utilisateur> getListeUsers(List<Utilisateur> userDTOs, int page, int ligneParPage) {
        return null;
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
        if(utilisateur == null)
            return "";
        return utilisateur.getPrenom() + " " +utilisateur.getNom();
    }

    @Override
    public void lireEnFonctionDuCode(String code) {
        var validation = validationService.lireEnFonctionDuCode(code);
        if (Instant.now().isAfter(validation.getExpiration())) {
            return;
        }
        Utilisateur utilisateur = utilisateurrRepository.findById(validation.getUtilisateur().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur inconnu"));
        utilisateur.setActif(true);
    }
}
