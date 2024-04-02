package com.meddjamm.sn.config.service;

import com.meddjamm.sn.config.entity.Utilisateur;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UtilisateurService {

    Utilisateur saveUtilisateur(Utilisateur utilisateur, String url);

    Utilisateur findUtilisateurByCode(String code);

    Utilisateur findUtilisateurById(Long utilisateurId);

    Utilisateur findUserById(Long utilisateurId);

    List<Utilisateur> findAllUtilisateurs();

    List<Utilisateur> findAllActives();

    boolean checkValiditePass(String mdp);

    Utilisateur findUtilisateurByEmail(String mail);

    List<Utilisateur> getListeUsers(List<Utilisateur> userDTOs, int page, int ligneParPage);

    public byte[] generateNewKey() throws NoSuchAlgorithmException;

    Utilisateur updateUserPass(Utilisateur utilisateur);

    void resetUserPass(Utilisateur utilisateur);

    String findNomComplet(Long id);

    String lireEnFonctionDuCode(String code);

    String validatePasswordResetToken(String token);

    Utilisateur findUserByPasswordToken(String token);

    void changePassword(Utilisateur theUser, String newPassword);

    boolean oldPasswordIsValid(Utilisateur user, String ancienMotDePasse);

    void createPasswordResetTokenForUser(Utilisateur user, String passwordResetToken);

    String demandeChangerMotDePasse(String email, String url);

    Utilisateur findUtilisateurByMatricule(String matricule);

    void deleteUtilisateur(String email);
}
