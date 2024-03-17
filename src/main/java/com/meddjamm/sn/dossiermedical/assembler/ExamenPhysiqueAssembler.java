package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.ExamenPhysique;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenPhysiqueDs;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ExamenPhysiqueAssembler {

    private final UtilisateurService utilisateurService;

    public ExamenPhysiqueAssembler(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public List<ExamenPhysiqueDs> assembleEntitiesFrom(List<ExamenPhysique> examenPhysiques) {
        return examenPhysiques.stream().map(this::assembleEntityToDs).toList();
    }

    public List<ExamenPhysique> listeEntitiesFromDs(List<ExamenPhysiqueDs> examenPhysiqueDs) {
        return examenPhysiqueDs.stream().map(this::assembleExamenPhysiqueFromDs).toList();
    }

    //
    public List<ExamenPhysiqueDs> createListExamenPhysiqueDs(Set<ExamenPhysique> examenPhysiques) {
        if (examenPhysiques == null) return Collections.emptyList();
        List<ExamenPhysiqueDs> dtos = new ArrayList<>();
        for (ExamenPhysique examenPhysique : examenPhysiques) {
            dtos.add(assembleEntityToDs(examenPhysique));
        }
        return dtos;
    }

    public Set<ExamenPhysique> createSetExamenPhysique(List<ExamenPhysiqueDs> examenPhysiqueDs) {
        if (examenPhysiqueDs == null) return null;
        Set<ExamenPhysique> examenPhysiques = new HashSet<>();
        for (ExamenPhysiqueDs dto : examenPhysiqueDs)
            if (dto != null) examenPhysiques.add(assembleExamenPhysiqueFromDs(dto));
        return examenPhysiques;
    }

    public ExamenPhysiqueDs assembleEntityToDs(ExamenPhysique examenPhysique) {
        ExamenPhysiqueDs examenPhysiqueDs = new ExamenPhysiqueDs();
        if (examenPhysique.getId() != null) examenPhysiqueDs.setId(examenPhysique.getId());
        examenPhysiqueDs.setActif(examenPhysique.isActif());
        examenPhysiqueDs.setCreatedDate(examenPhysique.getCreatedDate());
        examenPhysiqueDs.setExamenGeneral(examenPhysique.getExamenGeneral());
        examenPhysiqueDs.setExamenAppareil(examenPhysique.getExamenAppareil());
        examenPhysiqueDs.setPressionArterielS(examenPhysique.getPressionArterielS());
        examenPhysiqueDs.setPressionArterielD(examenPhysique.getPressionArterielD());
        examenPhysiqueDs.setTemperature(examenPhysique.getTemperature());
        examenPhysiqueDs.setFrequenceC(examenPhysique.getFrequenceC());
        examenPhysiqueDs.setFrequenceR(examenPhysique.getFrequenceR());
        examenPhysiqueDs.setSaturationOxygene(examenPhysique.getSaturationOxygene());
        examenPhysiqueDs.setDiurese(examenPhysique.getDiurese());
        examenPhysiqueDs.setPoids(examenPhysique.getPoids());
        examenPhysiqueDs.setTaille(examenPhysique.getTaille());
        examenPhysiqueDs.setImc(examenPhysique.getImc());
        examenPhysiqueDs.setTourTaille(examenPhysique.getTourTaille());
        examenPhysiqueDs.setTourHanche(examenPhysique.getTourHanche());
        examenPhysiqueDs.setGlycemie(examenPhysique.getGlycemie());
        if (examenPhysique.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(examenPhysique.getCreatedBy());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            examenPhysiqueDs.setNomCompletAgent(nomAgent);
        }
        return examenPhysiqueDs;
    }

    public ExamenPhysique assembleExamenPhysiqueFromDs(ExamenPhysiqueDs examenPhysiqueDs) {
        ExamenPhysique examenPhysique = new ExamenPhysique();
        examenPhysique.setId(examenPhysiqueDs.getId());
        examenPhysique.setActif(examenPhysiqueDs.isActif());
        examenPhysique.setExamenGeneral(examenPhysiqueDs.getExamenGeneral());
        examenPhysique.setExamenAppareil(examenPhysiqueDs.getExamenAppareil());
        examenPhysique.setCreatedDate(examenPhysiqueDs.getCreatedDate());
        examenPhysique.setPressionArterielS(examenPhysiqueDs.getPressionArterielS());
        examenPhysique.setPressionArterielD(examenPhysiqueDs.getPressionArterielD());
        examenPhysique.setTemperature(examenPhysiqueDs.getTemperature());
        examenPhysique.setFrequenceC(examenPhysiqueDs.getFrequenceC());
        examenPhysique.setFrequenceR(examenPhysiqueDs.getFrequenceR());
        examenPhysique.setSaturationOxygene(examenPhysiqueDs.getSaturationOxygene());
        examenPhysique.setDiurese(examenPhysiqueDs.getDiurese());
        examenPhysique.setPoids(examenPhysiqueDs.getPoids());
        examenPhysique.setTaille(examenPhysiqueDs.getTaille());
        examenPhysique.setImc(examenPhysiqueDs.getImc());
        examenPhysique.setTourTaille(examenPhysiqueDs.getTourTaille());
        examenPhysique.setTourHanche(examenPhysiqueDs.getTourHanche());
        examenPhysique.setGlycemie(examenPhysiqueDs.getGlycemie());
        return examenPhysique;
    }

}
