package com.meddjamm.sn.config.service;

import com.meddjamm.sn.config.entity.Utilisateur;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UtilisateurService {

    Utilisateur saveUtilisateur(Utilisateur utilisateur, String url);

    Utilisateur findUtilisateurByCode(String code) throws Exception;

    Utilisateur findUtilisateurById(Long utilisateurId);

    Utilisateur findUserById(Long utilisateurId);

    List<Utilisateur> findAllUtilisateurs();

    List<Utilisateur> findAllActives();

    boolean deleteUtilisateur(Utilisateur Utilisateur) throws Exception;

    boolean checkValiditePass(String mdp) throws Exception;

    Utilisateur findUtilisateurByEmail(String mail);

    //  List<Utilisateur> getListeUser(SearchDTO criteres) throws Exception;

    List<Utilisateur> getListeUsers(List<Utilisateur> userDTOs, int page, int ligneParPage);

    public byte[] generateNewKey() throws NoSuchAlgorithmException;

    //  TokenUser findDataByToken(String token);

    void updateUserPass(Utilisateur Utilisateur) throws Exception;

    void resetUserPass(Utilisateur Utilisateur) throws Exception;

    // ResponseEntity<ActivationDTO> findForActivation(String code);

    String findNomComplet(Long id);

    void lireEnFonctionDuCode(String code);

    void regenererMotDePassePourUtilisateur();

    String validatePasswordResetToken(String token);

    Utilisateur findUserByPasswordToken(String token);

    void changePassword(Utilisateur theUser, String newPassword);

    boolean oldPasswordIsValid(Utilisateur user, String ancienMotDePasse);

    void createPasswordResetTokenForUser(Utilisateur user, String passwordResetToken);

    String demandeChangerMotDePasse(String email, String url);

}
