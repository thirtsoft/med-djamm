package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.assembler.AntecedentChirurgieAssembler;
import com.meddjamm.sn.assembler.AntecedentFamilialAssembler;
import com.meddjamm.sn.assembler.AntecedentGynecologieAssembler;
import com.meddjamm.sn.assembler.AntecedentMedicauxAssembler;
import com.meddjamm.sn.dossiermedical.entity.ObservationClinique;
import com.meddjamm.sn.dossiermedical.remote.model.ObservationCliniqueDs;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class ObservationCliniqueAssembler {

    private final AntecedentMedicauxAssembler antecedentMedicauxAssembler;

    private final AntecedentChirurgieAssembler antecedentChirurgieAssembler;

    private final AntecedentGynecologieAssembler antecedentGynecologieAssembler;

    private final AntecedentFamilialAssembler antecedentFamilialAssembler;

    private final ExamenPhysiqueAssembler examenPhysiqueAssembler;

    private final AntecedentAssembler antecedentAssembler;

    public ObservationCliniqueAssembler(AntecedentMedicauxAssembler antecedentMedicauxAssembler,
                                        AntecedentChirurgieAssembler antecedentChirurgieAssembler,
                                        AntecedentGynecologieAssembler antecedentGynecologieAssembler,
                                        AntecedentFamilialAssembler antecedentFamilialAssembler,
                                        ExamenPhysiqueAssembler examenPhysiqueAssembler,
                                        AntecedentAssembler antecedentAssembler) {
        this.antecedentMedicauxAssembler = antecedentMedicauxAssembler;
        this.antecedentChirurgieAssembler = antecedentChirurgieAssembler;
        this.antecedentGynecologieAssembler = antecedentGynecologieAssembler;
        this.antecedentFamilialAssembler = antecedentFamilialAssembler;
        this.examenPhysiqueAssembler = examenPhysiqueAssembler;
        this.antecedentAssembler = antecedentAssembler;
    }


    public List<ObservationCliniqueDs> assembleEntitiesFrom(List<ObservationClinique> observationCliniques) {
        return observationCliniques.stream().map(this::assembleEntityToDs).toList();
    }

    public List<ObservationClinique> assembleEntitiesFromDs(List<ObservationCliniqueDs> observationCliniqueDs) {
        return observationCliniqueDs.stream().map(this::assembleObservationCliniqueFromDs).toList();
    }

    public ObservationCliniqueDs assembleEntityToDs(ObservationClinique observationClinique) {
        ObservationCliniqueDs observationCliniqueDs = new ObservationCliniqueDs();
        if (observationClinique.getId() != null)
            observationCliniqueDs.setId(observationClinique.getId());
        observationCliniqueDs.setActif(observationClinique.isActif());
        observationCliniqueDs.setMotifsHospitalisation(new ArrayList<>(observationClinique.getMotifsHospitalisation()));
        observationCliniqueDs.setHistoireMaladie(observationClinique.getHistoireMaladie());
        observationCliniqueDs.setCreatedDate(observationClinique.getCreatedDate());
        observationCliniqueDs.setAntecedentDs(antecedentAssembler.assembleEntityToDs(observationClinique.getAntecedent()));
        observationCliniqueDs.setExamenPhysiqueDs(examenPhysiqueAssembler.assembleEntityToDs(observationClinique.getExamenPhysique()));
        observationCliniqueDs.setCircuitPatientId(observationClinique.getCircuitPatientId());
        observationCliniqueDs.setCreatedBy(observationClinique.getCreatedBy());
        return observationCliniqueDs;
    }

    public ObservationClinique assembleObservationCliniqueFromDs(ObservationCliniqueDs observationCliniqueDs) {
        ObservationClinique observationClinique = new ObservationClinique();
        if (observationCliniqueDs.getId() != null)
            observationClinique.setId(observationCliniqueDs.getId());
        observationClinique.setActif(observationCliniqueDs.isActif());
        observationClinique.setMotifsHospitalisation(new HashSet<>(observationCliniqueDs.getMotifsHospitalisation()));
        observationClinique.setHistoireMaladie(observationCliniqueDs.getHistoireMaladie());
        observationClinique.setCreatedDate(observationCliniqueDs.getCreatedDate());
        observationClinique.setExamenPhysique(examenPhysiqueAssembler.assembleExamenPhysiqueFromDs(observationCliniqueDs.getExamenPhysiqueDs()));
        observationClinique.setAntecedent(antecedentAssembler.assembleAntecedentFromDs(observationCliniqueDs.getAntecedentDs()));
        observationClinique.setCircuitPatientId(observationCliniqueDs.getCircuitPatientId());
        observationClinique.setCreatedBy(observationCliniqueDs.getCreatedBy());
        return observationClinique;
    }

}
