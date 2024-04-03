package com.meddjamm.sn.config.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.config.remote.model.UtilisateurProfilDs;
import com.meddjamm.sn.config.service.ProfilService;
import com.meddjamm.sn.config.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UtilisateurAssembler {

    private final ProfilService profilService;
    private final UtilisateurService utilisateurService;

    public List<UtilisateurDs> assembleEntitiesFrom(List<Utilisateur> utilisateurs) {
        return utilisateurs.stream().map(this::assembleUtilisateurDsFromEntity).toList();
    }

    public Utilisateur assembleUtilisateurFromDs(UtilisateurDs utilisateurDs) {
        Utilisateur utilisateur = new Utilisateur();
        if (utilisateurDs.getId() != null) {
            utilisateur.setId(utilisateurDs.getId());
        }
        utilisateur.setCodeUtilisateur(utilisateurDs.getCodeUtilisateur());
        utilisateur.setMotdepasseprecedent(utilisateurDs.getMotdepasseprecedent());
        utilisateur.setEst_valide(utilisateurDs.isEst_valide());
        utilisateur.setMdpamodifier(utilisateurDs.isMdpamodifier());
        utilisateur.setPrenom(utilisateurDs.getPrenom());
        utilisateur.setNom(utilisateurDs.getNom());
        utilisateur.setEmail(utilisateurDs.getEmail());
        utilisateur.setTelephone(utilisateurDs.getTelephone());
        utilisateur.setProfil(profilService.findByProfilCode(utilisateurDs.getProfileCode()));
        return utilisateur;
    }

    public Utilisateur assembleUtilisateurForUpdateDs(UtilisateurDs utilisateurDs) {
        Utilisateur utilisateur = utilisateurService.findUtilisateurByEmail(utilisateurDs.getEmail());
        utilisateur.setCodeUtilisateur(utilisateurDs.getCodeUtilisateur());
        utilisateur.setPrenom(utilisateurDs.getPrenom());
        utilisateur.setNom(utilisateurDs.getNom());
        utilisateur.setTelephone(utilisateurDs.getTelephone());
        utilisateur.setCivilite(utilisateurDs.getCivilite());
        utilisateur.setSexe(utilisateurDs.getSexe());
        utilisateur.setFonction(utilisateurDs.getFonction());
        utilisateur.setAdresse(utilisateurDs.getAdresse());
        utilisateur.setTypeUtilisateur(utilisateurDs.getTypeUtilisateur());
        utilisateur.setProfil(profilService.findByProfilCode(utilisateurDs.getProfileCode()));
        return utilisateur;
    }

    public UtilisateurDs assembleUtilisateurDsFromEntity(Utilisateur utilisateur) {
        UtilisateurDs utilisateurDs = new UtilisateurDs();
        if (utilisateur.getId() != null)
            utilisateurDs.setId(utilisateur.getId());
        utilisateurDs.setCodeUtilisateur(utilisateur.getCodeUtilisateur());
        utilisateurDs.setProfileCode(utilisateur.getProfil().getLibelle());
        utilisateurDs.setEst_valide(utilisateur.isEst_valide());
        utilisateurDs.setMdpamodifier(utilisateur.isMdpamodifier());
        utilisateurDs.setPrenom(utilisateur.getPrenom());
        utilisateurDs.setNom(utilisateur.getNom());
        utilisateurDs.setEmail(utilisateur.getEmail());
        utilisateurDs.setTelephone(utilisateur.getTelephone());
        utilisateurDs.setMatricule(utilisateur.getMatricule());
        utilisateurDs.setFonction(utilisateur.getFonction());
        utilisateurDs.setAdresse(utilisateur.getAdresse());
        utilisateurDs.setTypeUtilisateur(utilisateur.getTypeUtilisateur());
        utilisateurDs.setCivilite(utilisateur.getCivilite());
        utilisateurDs.setSexe(utilisateur.getSexe());
        return utilisateurDs;
    }

    public UtilisateurProfilDs assembleUtilisateurProfilDsFromEntity(Utilisateur utilisateur) {
        UtilisateurProfilDs utilisateurDs = new UtilisateurProfilDs();
        if (utilisateur.getId() != null)
            utilisateurDs.setId(utilisateur.getId());
        utilisateurDs.setCodeUtilisateur(utilisateur.getCodeUtilisateur());
        utilisateurDs.setPrenom(utilisateur.getPrenom());
        utilisateurDs.setNom(utilisateur.getNom());
        utilisateurDs.setEmail(utilisateur.getEmail());
        utilisateurDs.setTelephone(utilisateur.getTelephone());
        utilisateurDs.setProfileCode(utilisateur.getProfil().getLibelle());
        utilisateurDs.setMatricule(utilisateur.getMatricule());
        utilisateurDs.setCivilite(utilisateur.getCivilite());
        utilisateurDs.setSexe(utilisateur.getSexe());
        utilisateurDs.setFonction(utilisateur.getFonction());
        utilisateurDs.setAdresse(utilisateur.getAdresse());
        utilisateurDs.setTypeUtilisateur(utilisateur.getTypeUtilisateur());
        return utilisateurDs;
    }
}
