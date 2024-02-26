package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.DossierMedicalInformatise;
import com.meddjamm.sn.repository.DossierMedicalInformatiseRepository;
import com.meddjamm.sn.services.DossierMedicalInformatiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DossierMedicalInformatiseServiceImpl implements DossierMedicalInformatiseService {

    private final DossierMedicalInformatiseRepository dossierMedicalInformatiseRepository;

    public DossierMedicalInformatiseServiceImpl(DossierMedicalInformatiseRepository dossierMedicalInformatiseRepository) {
        this.dossierMedicalInformatiseRepository = dossierMedicalInformatiseRepository;
    }

    @Override
    public void saveDossierMedicalInformatise(DossierMedicalInformatise dossierMedicalInformatise) {
        if (findByNumero(dossierMedicalInformatise.getNumeroDossierMedical()) != null)
            log.info("This dossier exist");
        dossierMedicalInformatise.setActif(true);
        dossierMedicalInformatise.setCreateDate(new Date());
        dossierMedicalInformatiseRepository.save(dossierMedicalInformatise);

    }

    @Override
    public void updateDossierMedicalInformatise(Long id, DossierMedicalInformatise dossierMedicalInformatise) throws Exception {
        if (!dossierMedicalInformatiseRepository.existsById(id))
            log.info("Dossier medical that id is " + id + "not found");
        dossierMedicalInformatise.setId(id);
        dossierMedicalInformatiseRepository.save(dossierMedicalInformatise);
    }

    @Override
    public DossierMedicalInformatise findById(Long id) {
        return dossierMedicalInformatiseRepository.findDossierMedicalInformatiseById(id);
    }

    @Override
    public DossierMedicalInformatise findByNumero(int numero) {
        return dossierMedicalInformatiseRepository.findDossierMedicalInformatiseByNumero(numero);
    }

    @Override
    public List<DossierMedicalInformatise> findAllDossierMedicalInformatises() {
        return dossierMedicalInformatiseRepository.findAllDossierMedicalInformatises();
    }

    @Override
    public List<DossierMedicalInformatise> findDossierMedicalInformatisesByPatient(String code) {
        return dossierMedicalInformatiseRepository.findDossierMedicalInformatiseByPatient(code);
    }

    @Override
    public void deleteDossierMedicalInformatise(Long id) {
        DossierMedicalInformatise dossierMedicalInformatise = findById(id);
        dossierMedicalInformatise.setActif(false);
        dossierMedicalInformatiseRepository.save(dossierMedicalInformatise);
    }
}
