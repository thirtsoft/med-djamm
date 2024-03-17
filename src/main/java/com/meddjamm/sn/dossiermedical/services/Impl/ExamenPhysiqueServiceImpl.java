package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.ExamenPhysique;
import com.meddjamm.sn.dossiermedical.entity.ObservationClinique;
import com.meddjamm.sn.dossiermedical.repository.ExamenPhysiqueRepository;
import com.meddjamm.sn.dossiermedical.repository.ObservationCliniqueRepository;
import com.meddjamm.sn.dossiermedical.services.ExamenPhysiqueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ExamenPhysiqueServiceImpl implements ExamenPhysiqueService {

    private final ExamenPhysiqueRepository examenPhysiqueRepository;

    private final ObservationCliniqueRepository observationCliniqueRepository;

    public ExamenPhysiqueServiceImpl(ExamenPhysiqueRepository examenPhysiqueRepository,
                                     ObservationCliniqueRepository observationCliniqueRepository) {
        this.examenPhysiqueRepository = examenPhysiqueRepository;
        this.observationCliniqueRepository = observationCliniqueRepository;
    }

    @Override
    public ExamenPhysique saveExamenPhysique(ExamenPhysique examen) {
        examen.setActif(true);
        examen.setCreatedDate(new Date());
        ObservationClinique observationClinique = observationCliniqueRepository.findObservationCliniqueById(examen.getObservationCliniqueId());
        examen.setObservationCliniqueId(examen.getObservationCliniqueId());
        examen.setObservationClinique(observationClinique);
        return examenPhysiqueRepository.save(examen);
    }

    @Override
    public ExamenPhysique updateExamenPhysique(Long id, ExamenPhysique examen) throws Exception {
        if (!examenPhysiqueRepository.existsById(id)) {
            log.info("Examen physique that id is " + id + "not found");
        }
        examen.setId(id);
        return examenPhysiqueRepository.save(examen);
    }

    @Override
    public ExamenPhysique findById(Long id) {
        return examenPhysiqueRepository.findExamenPhysiqueById(id);
    }

    @Override
    public List<ExamenPhysique> findAllExamenPhysiques() {
        return examenPhysiqueRepository.findAll();
    }

    @Override
    public List<ExamenPhysique> findAllExamenPhysiqueByObservationClinique(Long observationCliniqueId) {
        return examenPhysiqueRepository.findAllExamenPhysiquesByObservationCliniqueId(observationCliniqueId);
    }

    @Override
    public void deleteExamenPhysique(Long id) {
        ExamenPhysique examenPhysique = findById(id);
        examenPhysique.setActif(false);
        examenPhysiqueRepository.save(examenPhysique);
    }
}