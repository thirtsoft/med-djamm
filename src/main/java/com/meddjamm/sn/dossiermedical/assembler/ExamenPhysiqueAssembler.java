package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.dossiermedical.entity.ExamenPhysique;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenPhysiqueDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamenPhysiqueAssembler {

    public List<ExamenPhysiqueDs> assembleEntitiesFrom(List<ExamenPhysique> examenPhysiques) {
        return examenPhysiques.stream().map(this::assembleEntityToDs).toList();
    }

    public ExamenPhysiqueDs assembleEntityToDs(ExamenPhysique examenPhysique) {
        ExamenPhysiqueDs examenPhysiqueDs = new ExamenPhysiqueDs();
        if (examenPhysique.getId() != null)
            examenPhysiqueDs.setId(examenPhysique.getId());
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
        return examenPhysique;
    }

}
