package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.DossierPatient;
import com.meddjamm.sn.repository.DossierPatientRepository;
import com.meddjamm.sn.services.DossierPatientService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DossierPatientServiceImpl implements DossierPatientService {

    private final DossierPatientRepository dossierPatientRepository;

    public DossierPatientServiceImpl(DossierPatientRepository dossierPatientRepository) {
        this.dossierPatientRepository = dossierPatientRepository;
    }

    @Override
    public void saveDossierPatient(DossierPatient dossierPatient) {
        dossierPatient.setActif(true);
        dossierPatient.setCreateDate(new Date());
        dossierPatientRepository.save(dossierPatient);

    }

    @Override
    public void updateDossierPatient(Long id, DossierPatient dossierPatient) throws Exception {

    }

    @Override
    public DossierPatient findById(Long id) {
        return dossierPatientRepository.findDossierPatientById(id);
    }

    @Override
    public List<DossierPatient> findAllDossierPatients() {
        return dossierPatientRepository.findAllDossierPatients();
    }

    @Override
    public List<DossierPatient> findDossierPatientsByPatient(String indexPatient) {
        return dossierPatientRepository.findDossierPatientsByPatient(indexPatient);
    }

    @Override
    public void deleteDossierPatient(Long id) {
        DossierPatient dossierPatient = findById(id);
        dossierPatient.setActif(false);
        dossierPatientRepository.save(dossierPatient);
    }
}
