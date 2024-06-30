package com.meddjamm.sn.config.service;

import com.meddjamm.sn.config.entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    Long saveUtilisateur(Utilisateur utilisateur, String url) throws Exception;

    Utilisateur findUtilisateurById(Long utilisateurId);

    Utilisateur findUserById(Long utilisateurId);

    List<Utilisateur> findAllUtilisateurs();

    Utilisateur findUtilisateurByEmail(String mail);

    Utilisateur updateUserPass(Utilisateur utilisateur);

    String lireEnFonctionDuCode(String code);

    void changePassword(Utilisateur theUser, String newPassword);

    boolean oldPasswordIsValid(Utilisateur user, String ancienMotDePasse);

    void demandeResetMotDePasse(String email);

    Utilisateur findUtilisateurByMatricule(String matricule);

    void deleteUtilisateur(String email);

    List<Utilisateur> findAllMedecins();

    void activatedAccount(String matricule);

    void deactivatedAccount(String matricule);
}