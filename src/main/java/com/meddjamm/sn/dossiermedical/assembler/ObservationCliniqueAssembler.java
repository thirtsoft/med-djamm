package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.ObservationClinique;
import com.meddjamm.sn.dossiermedical.remote.model.ObservationCliniqueDs;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class ObservationCliniqueAssembler {

    private final ExamenPhysiqueAssembler examenPhysiqueAssembler;

    private final AntecedentAssembler antecedentAssembler;

    private final UtilisateurService utilisateurService;

    public ObservationCliniqueAssembler(ExamenPhysiqueAssembler examenPhysiqueAssembler,
                                        AntecedentAssembler antecedentAssembler,
                                        UtilisateurService utilisateurService) {
        this.examenPhysiqueAssembler = examenPhysiqueAssembler;
        this.antecedentAssembler = antecedentAssembler;
        this.utilisateurService = utilisateurService;
    }


    public List<ObservationCliniqueDs> assembleEntitiesFrom(List<ObservationClinique> observationCliniques) {
        return observationCliniques.stream().map(this::assembleEntityToDs).toList();
    }

    public List<ObservationClinique> assembleEntitiesFromDs(List<ObservationCliniqueDs> observationCliniqueDs) {
        return observationCliniqueDs.stream().map(this::assembleObservationCliniqueFromDs).toList();
    }

    public ObservationCliniqueDs assembleEntityToDs(ObservationClinique observationClinique) {
        ObservationCliniqueDs observationCliniqueDs = new ObservationCliniqueDs();
        if (observationClinique.getId() != null) observationCliniqueDs.setId(observationClinique.getId());
        observationCliniqueDs.setActif(observationClinique.isActif());
        observationCliniqueDs.setMotifsHospitalisation(new ArrayList<>(observationClinique.getMotifsHospitalisation()));
        observationCliniqueDs.setHistoireMaladie(observationClinique.getHistoireMaladie());
        observationCliniqueDs.setCreatedDate(observationClinique.getCreatedDate());
        observationCliniqueDs.setAntecedentDs(antecedentAssembler.assembleEntityToDs(observationClinique.getAntecedent()));
        observationCliniqueDs.setExamenPhysiqueDs(examenPhysiqueAssembler.assembleEntitiesFrom(observationClinique.getExamenPhysiqueList()));
        // observationCliniqueDs.setExamenPhysiqueDs(examenPhysiqueAssembler.createListExamenPhysiqueDs(observationClinique.getExamenPhysiqueList()));
        observationCliniqueDs.setCircuitPatientId(observationClinique.getCircuitPatientId());
        observationCliniqueDs.setCreatedBy(observationClinique.getCreatedBy());
        if (observationClinique.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(observationClinique.getCreatedBy());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            observationCliniqueDs.setNomCompletAgent(nomAgent);
        }
        return observationCliniqueDs;
    }

    public ObservationClinique assembleObservationCliniqueFromDs(ObservationCliniqueDs observationCliniqueDs) {
        ObservationClinique observationClinique = new ObservationClinique();
        if (observationCliniqueDs.getId() != null) observationClinique.setId(observationCliniqueDs.getId());
        observationClinique.setActif(observationCliniqueDs.isActif());
        observationClinique.setMotifsHospitalisation(new HashSet<>(observationCliniqueDs.getMotifsHospitalisation()));
        observationClinique.setHistoireMaladie(observationCliniqueDs.getHistoireMaladie());
        observationClinique.setCreatedDate(observationCliniqueDs.getCreatedDate());
        observationClinique.setExamenPhysiqueList(examenPhysiqueAssembler.listeEntitiesFromDs(observationCliniqueDs.getExamenPhysiqueDs()));
        // observationClinique.setExamenPhysiqueList(examenPhysiqueAssembler.createSetExamenPhysique(observationCliniqueDs.getExamenPhysiqueDs()));
        observationClinique.setAntecedent(antecedentAssembler.assembleAntecedentFromDs(observationCliniqueDs.getAntecedentDs()));
        observationClinique.setCircuitPatientId(observationCliniqueDs.getCircuitPatientId());
        observationClinique.setCreatedBy(observationCliniqueDs.getCreatedBy());
        return observationClinique;
    }

}
