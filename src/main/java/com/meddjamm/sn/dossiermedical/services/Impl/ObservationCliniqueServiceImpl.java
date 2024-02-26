package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.assembler.ObservationCliniqueAssembler;
import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.entity.ObservationClinique;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.ObservationCliniqueRepository;
import com.meddjamm.sn.dossiermedical.services.ObservationCliniqueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ObservationCliniqueServiceImpl implements ObservationCliniqueService {

    private final ObservationCliniqueRepository observationCliniqueRepository;
    private final CircuitPatientRepository circuitPatientRepository;
    private final ObservationCliniqueAssembler observationCliniqueAssembler;

    public ObservationCliniqueServiceImpl(ObservationCliniqueRepository observationCliniqueRepository,
                                          CircuitPatientRepository circuitPatientRepository,
                                          ObservationCliniqueAssembler observationCliniqueAssembler) {
        this.observationCliniqueRepository = observationCliniqueRepository;
        this.circuitPatientRepository = circuitPatientRepository;
        this.observationCliniqueAssembler = observationCliniqueAssembler;
    }

    @Override
    public void saveObservationClinique(ObservationClinique observationClinique) {
        observationClinique.setActif(true);
        observationClinique.setCreatedDate(new Date());
        CircuitPatient circuitPatient = circuitPatientRepository.findCircuitPatientById(observationClinique.getCircuitPatientId());
        observationClinique.setCircuitPatientId(circuitPatient.getId());
        observationClinique.setCircuitPatient(circuitPatient);
        observationCliniqueRepository.save(observationClinique);
    }

    @Override
    public void updateObservationClinique(Long id, ObservationClinique observationClinique) throws Exception {
        if (!observationCliniqueRepository.existsById(id)) {
            log.info("Observation clinique that id is " + id + "not found");
        }
        observationClinique.setId(id);
        observationCliniqueRepository.save(observationClinique);
    }

    @Override
    public ObservationClinique findById(Long id) {
        return observationCliniqueRepository.findObservationCliniqueById(id);
    }

    @Override
    public List<ObservationClinique> findAllObservationCliniques() {
        return observationCliniqueRepository.findAllObservationCliniques();
    }

    @Override
    public void deleteObservationClinique(Long id) {
        ObservationClinique observationClinique = findById(id);
        observationClinique.setActif(false);
        observationCliniqueRepository.save(observationClinique);
    }

    @Override
    public List<ObservationClinique> findObservationCliniqueByPatientId(String code) {
        return observationCliniqueRepository.findObservationCliniqueByPatientId(code);
    }
}
