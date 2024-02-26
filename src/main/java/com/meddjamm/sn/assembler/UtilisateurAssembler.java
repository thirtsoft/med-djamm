package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.Utilisateur;
import com.meddjamm.sn.remote.model.UtilisateurDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UtilisateurAssembler {

    public List<UtilisateurDs> assembleEntitiesFrom(List<Utilisateur> utilisateurs) {
        return utilisateurs.stream().map(this::assembleUtilisateurDsFromEntity).toList();
    }

    public Utilisateur assembleUtilisateurFromDs(UtilisateurDs utilisateurDs) {
        Utilisateur utilisateur = new Utilisateur();
        if (utilisateurDs.getId() != null)
            utilisateur.setId(utilisateurDs.getId());
        utilisateur.setCodeUtilisateur(utilisateurDs.getCodeUtilisateur());
        utilisateur.setMotdepasse(utilisateurDs.getMotdepasse());
        utilisateur.setMotdepasseprecedent(utilisateurDs.getMotdepasseprecedent());
        utilisateur.setEst_valide(utilisateurDs.isEst_valide());
        utilisateur.setMdpamodifier(utilisateurDs.isMdpamodifier());
        utilisateur.setPrenom(utilisateurDs.getPrenom());
        utilisateur.setNom(utilisateurDs.getNom());
        utilisateur.setEmail(utilisateurDs.getEmail());
        utilisateur.setTelephone(utilisateurDs.getTelephone());
        utilisateur.setDateCreation(utilisateurDs.getDateCreation());
        utilisateur.setDateModPass(utilisateurDs.getDateModPass());
        utilisateur.setResteValidite(utilisateurDs.getResteValidite());
        utilisateur.setProfilId(utilisateurDs.getProfilId());
        utilisateur.setActivation(utilisateurDs.getActivation());
        utilisateur.setCreatedBy(utilisateurDs.getCreatedBy());
        return utilisateur;
    }

    public UtilisateurDs assembleUtilisateurDsFromEntity(Utilisateur utilisateur) {
        UtilisateurDs utilisateurDs = new UtilisateurDs();
        if (utilisateur.getId() != null)
            utilisateurDs.setId(utilisateur.getId());
        utilisateurDs.setCodeUtilisateur(utilisateur.getCodeUtilisateur());
        utilisateurDs.setMotdepasse(utilisateur.getMotdepasse());
        utilisateurDs.setMotdepasseprecedent(utilisateur.getMotdepasseprecedent());
        utilisateurDs.setEst_valide(utilisateur.isEst_valide());
        utilisateurDs.setMdpamodifier(utilisateur.isMdpamodifier());
        utilisateurDs.setPrenom(utilisateur.getPrenom());
        utilisateurDs.setNom(utilisateur.getNom());
        utilisateurDs.setEmail(utilisateur.getEmail());
        utilisateurDs.setTelephone(utilisateur.getTelephone());
        utilisateurDs.setDateCreation(utilisateur.getDateCreation());
        utilisateurDs.setDateModPass(utilisateur.getDateModPass());
        utilisateurDs.setResteValidite(utilisateur.getResteValidite());
        utilisateurDs.setProfilId(utilisateur.getProfilId());
        utilisateurDs.setActivation(utilisateur.getActivation());
        utilisateurDs.setCreatedBy(utilisateur.getCreatedBy());
        return utilisateurDs;
    }
}
