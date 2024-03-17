package com.meddjamm.sn.config.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.config.service.ProfilService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UtilisateurAssembler {

    private final ProfilService profilService;
    private final PasswordEncoder passwordEncoder;

    public List<UtilisateurDs> assembleEntitiesFrom(List<Utilisateur> utilisateurs) {
        return utilisateurs.stream().map(this::assembleUtilisateurDsFromEntity).toList();
    }

    public Utilisateur assembleUtilisateurFromDs(UtilisateurDs utilisateurDs) {
        Utilisateur utilisateur = new Utilisateur();
        if (utilisateurDs.getId() != null) {
            utilisateur.setId(utilisateurDs.getId());
        }
        utilisateur.setCodeUtilisateur(utilisateurDs.getCodeUtilisateur());
        utilisateur.setMotdepasse(passwordEncoder.encode(utilisateurDs.getMotDePasse()));
        utilisateur.setMotdepasseprecedent(utilisateurDs.getMotdepasseprecedent());
        utilisateur.setEst_valide(utilisateurDs.isEst_valide());
        utilisateur.setMdpamodifier(utilisateurDs.isMdpamodifier());
        utilisateur.setPrenom(utilisateurDs.getPrenom());
        utilisateur.setNom(utilisateurDs.getNom());
        utilisateur.setEmail(utilisateurDs.getEmail());
        utilisateur.setTelephone(utilisateurDs.getTelephone());
        utilisateur.setDateModPass(utilisateurDs.getDateModPass());
        utilisateur.setResteValidite(utilisateurDs.getResteValidite());
        utilisateur.setProfilId(utilisateurDs.getProfilId());
        utilisateur.setActivation(utilisateurDs.getActivation());
        utilisateur.setProfil(profilService.findByCode(utilisateurDs.getProfileCode()));
        return utilisateur;
    }

    public UtilisateurDs assembleUtilisateurDsFromEntity(Utilisateur utilisateur) {
        UtilisateurDs utilisateurDs = new UtilisateurDs();
        if (utilisateur.getId() != null)
            utilisateurDs.setId(utilisateur.getId());
        utilisateurDs.setCodeUtilisateur(utilisateur.getCodeUtilisateur());
        utilisateurDs.setMotDePasse(utilisateur.getMotdepasse());
        utilisateurDs.setMotdepasseprecedent(utilisateur.getMotdepasseprecedent());
        utilisateurDs.setEst_valide(utilisateur.isEst_valide());
        utilisateurDs.setMdpamodifier(utilisateur.isMdpamodifier());
        utilisateurDs.setPrenom(utilisateur.getPrenom());
        utilisateurDs.setNom(utilisateur.getNom());
        utilisateurDs.setEmail(utilisateur.getEmail());
        utilisateurDs.setTelephone(utilisateur.getTelephone());
        utilisateurDs.setDateModPass(utilisateur.getDateModPass());
        utilisateurDs.setResteValidite(utilisateur.getResteValidite());
        utilisateurDs.setProfilId(utilisateur.getProfilId());
        utilisateurDs.setActivation(utilisateur.getActivation());
        utilisateurDs.setMatricule(utilisateur.getMatricule());
        return utilisateurDs;
    }
}
