package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.ObservationClinique;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.ExamenPhysiqueRepository;
import com.meddjamm.sn.dossiermedical.repository.ObservationCliniqueRepository;
import com.meddjamm.sn.dossiermedical.services.ObservationCliniqueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ObservationCliniqueServiceImpl implements ObservationCliniqueService {

    private final ObservationCliniqueRepository observationCliniqueRepository;
    private final CircuitPatientRepository circuitPatientRepository;
    private final ExamenPhysiqueRepository examenPhysiqueRepository;

    public ObservationCliniqueServiceImpl(ObservationCliniqueRepository observationCliniqueRepository,
                                          CircuitPatientRepository circuitPatientRepository,
                                          ExamenPhysiqueRepository examenPhysiqueRepository) {
        this.observationCliniqueRepository = observationCliniqueRepository;
        this.circuitPatientRepository = circuitPatientRepository;
        this.examenPhysiqueRepository = examenPhysiqueRepository;
    }

    @Override
    public void saveObservationClinique(ObservationClinique observationClinique) {
        observationClinique.setActif(true);
        observationClinique.setCreatedDate(new Date());
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
}
