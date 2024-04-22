package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.repository.PatientRepository;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        patient.setActif(true);
        patient.setDateAdmission(new Date());
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) throws Exception {
        if (!patientRepository.existsById(id)) {
            throw new Exception("This Patient is not found");
        }
        Patient patientResult = patientRepository.findPatientById(id);
        if (patientResult == null) {
            throw new Exception("This Patient is not found");
        }
        patientResult.setPrenom(patient.getPrenom());
        patientResult.setNom(patient.getNom());
        patientResult.setAddress(patient.getAddress());
        patientResult.setSexe(patient.getSexe());
        patientResult.setNumeroTelephone(patient.getNumeroTelephone());
        patientResult.setCivilite(patient.getCivilite());
        patientResult.setDateNaissance(patient.getDateNaissance());
        patientResult.setAge(patient.getAge());
        patientResult.setNationalite(patient.getNationalite());
        patientResult.setProfession(patient.getProfession());
        patientResult.setSituationMatrimonial(patient.getSituationMatrimonial());
        patientResult.setPersonneConfiance(patient.getPersonneConfiance());
        return patientRepository.save(patientResult);
    }

    @Override
    public void updatePatientByMedeccin(Long id, Patient patient) throws Exception {
        if (!patientRepository.existsById(id)) {
            throw new Exception("This Patient is not found");
        }
        Patient patientResult = patientRepository.findPatientById(id);
        if (patientResult == null) {
            throw new Exception("This Patient is not found");
        }
        patientResult.setPrenom(patient.getPrenom());
        patientResult.setNom(patient.getNom());
        patientResult.setSexe(patient.getSexe());
        patientResult.setCivilite(patient.getCivilite());
        patientResult.setProfession(patient.getProfession());
        patientResult.setSituationMatrimonial(patient.getSituationMatrimonial());
        patientResult.setRace(patient.getRace());
        patientResult.setEthnie(patient.getEthnie());
        patientResult.setOrigine(patient.getOrigine());
        patientResult.setOrigineMere(patient.getOrigineMere());
        patientResult.setOriginePere(patient.getOriginePere());
        patientResult.setPrototype(patient.getPrototype());
        patientResult.setConsanguinite(patient.getConsanguinite());
        patientResult.setNiveauSocialEconomique(patient.getNiveauSocialEconomique());
        patientResult.setRegimeAlimentaire(patient.getRegimeAlimentaire());
        patientRepository.save(patientResult);
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findPatientById(id);
    }

    @Override
    public Patient findByCode(String code) {
        return patientRepository.findPatientByCode(code);
    }

    @Override
    public List<Patient> findAllPatients() {
        return patientRepository.findAllPatients();
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findPatientById(id);
        patient.setActif(false);
        patientRepository.save(patient);
    }

    @Override
    public Page<Patient> findAllPatients(Integer pageNumber, Integer pageSize) {
        return patientRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public long countActivePatient() {
        return patientRepository.countActivePatient();
    }
}
