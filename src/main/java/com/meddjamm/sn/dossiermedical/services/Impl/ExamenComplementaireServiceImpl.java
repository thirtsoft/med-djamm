package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.ExamenComplementaire;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.ExamenComplementaireRepository;
import com.meddjamm.sn.dossiermedical.services.ExamenComplementaireService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExamenComplementaireServiceImpl implements ExamenComplementaireService {

    private final ExamenComplementaireRepository examenComplementaireRepository;
    private final CircuitPatientRepository circuitPatientRepository;

    public ExamenComplementaireServiceImpl(ExamenComplementaireRepository examenComplementaireRepository,
                                           CircuitPatientRepository circuitPatientRepository) {
        this.examenComplementaireRepository = examenComplementaireRepository;
        this.circuitPatientRepository = circuitPatientRepository;
    }

    @Override
    public ExamenComplementaire saveExamenComplementaire(ExamenComplementaire examen) {
        examen.setActif(true);
        return examenComplementaireRepository.save(examen);
    }

    @Override
    public ExamenComplementaire updateExamenComplementaire(Long id, ExamenComplementaire examen) throws Exception {
        if (!examenComplementaireRepository.existsById(id)) {
            throw new Exception("Examen that this id " + id + "not found");
        }
        examen.setId(id);
        return examenComplementaireRepository.save(examen);
    }

    @Override
    public ExamenComplementaire findById(Long id) {
        return examenComplementaireRepository.findExamenComplementaireById(id);
    }

    @Override
    public List<ExamenComplementaire> findAllExamenComplementaires() {
        return examenComplementaireRepository.findAllExamenComplementaires();
    }

    @Override
    public void deleteExamenComplementaire(Long id) {
        ExamenComplementaire examenComplementaire = findById(id);
        examenComplementaire.setActif(false);
        examenComplementaireRepository.save(examenComplementaire);
    }

    @Override
    public List<ExamenComplementaire> findExamenComplementaireByPatientId(String code) {
        return examenComplementaireRepository.findExamenComplementaireByPatientId(code);
    }
}
