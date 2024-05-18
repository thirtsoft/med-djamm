package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.ExamenBiologique;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.ExamenBiologiqueRepository;
import com.meddjamm.sn.dossiermedical.services.ExamenBiologiqueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExamenBiologiqueServiceImpl implements ExamenBiologiqueService {

    private final ExamenBiologiqueRepository examenBiologiqueRepository;

    private final CircuitPatientRepository circuitPatientRepository;

    public ExamenBiologiqueServiceImpl(ExamenBiologiqueRepository examenBiologiqueRepository,
                                       CircuitPatientRepository circuitPatientRepository) {
        this.examenBiologiqueRepository = examenBiologiqueRepository;
        this.circuitPatientRepository = circuitPatientRepository;
    }

    @Override
    public ExamenBiologique saveExamenBiologique(ExamenBiologique examen) {
        examen.setActif(true);
        return examenBiologiqueRepository.save(examen);
    }

    @Override
    public ExamenBiologique updateExamenBiologique(Long id, ExamenBiologique examen) throws Exception {
        if (!examenBiologiqueRepository.existsById(id)) {
            log.info("Examen that this id " + id + "not found");
        }
        examen.setId(id);
        return examenBiologiqueRepository.save(examen);
    }

    @Override
    public ExamenBiologique findById(Long id) {
        return examenBiologiqueRepository.findExamenBiologiqueById(id);
    }

    @Override
    public List<ExamenBiologique> findAllExamenBiologiques() {
        return examenBiologiqueRepository.findAllExamenBiologiques();
    }

    @Override
    public void deleteExamenBiologique(Long id) {
        ExamenBiologique examenBiologique = examenBiologiqueRepository.findExamenBiologiqueById(id);
        examenBiologique.setActif(false);
        examenBiologiqueRepository.save(examenBiologique);
    }

    @Override
    public List<ExamenBiologique> findExamenBiologiqueByPatientId(String code) {
        return examenBiologiqueRepository.findExamenBiologiquesByPatientId(code);
    }
}
