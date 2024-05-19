package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.Synthese;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.SyntheseRepository;
import com.meddjamm.sn.dossiermedical.services.SyntheseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SyntheseServiceImpl implements SyntheseService {

    private final SyntheseRepository syntheseRepository;

    private final CircuitPatientRepository circuitPatientRepository;

    public SyntheseServiceImpl(SyntheseRepository syntheseRepository,
                               CircuitPatientRepository circuitPatientRepository) {
        this.syntheseRepository = syntheseRepository;
        this.circuitPatientRepository = circuitPatientRepository;
    }

    @Override
    public Synthese saveSynthese(Synthese synthese) {
        synthese.setActif(true);
        return syntheseRepository.save(synthese);
    }

    @Override
    public Synthese updateSynthese(Long id, Synthese synthese) throws Exception {
        if (!syntheseRepository.existsById(id)) {
            new Exception("This Systhese that id is " + id + "not found");
        }
        synthese.setId(id);
        return syntheseRepository.save(synthese);
    }

    @Override
    public Synthese findById(Long id) {
        return syntheseRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "This Syhnthes that id is " + id + "not found"
        ));
    }

    @Override
    public List<Synthese> findAllSyntheses() {
        return syntheseRepository.findAllSyntheses();
    }

    @Override
    public void deleteSynthese(Long id) {
        Synthese synthese = syntheseRepository.findSyntheseById(id);
        synthese.setActif(false);
        syntheseRepository.save(synthese);
    }
}
