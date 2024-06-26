package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.model.Patient;
import com.meddjamm.sn.repository.PatientRepository;
import com.meddjamm.sn.services.PatientService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        patient.setActif(true);
        patient.setDateInscription(new Date());
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
        patientResult.setConsanguinite(patient.getConsanguinite());
        patientResult.setEthnie(patient.getEthnie());
        patientResult.setNationalite(patient.getNationalite());
        patientResult.setNiveauSocialEconomique(patient.getNiveauSocialEconomique());
        patientResult.setPhoto(patient.getPhoto());
        patientResult.setOrigine(patient.getOrigine());
        patientResult.setOrigineMere(patient.getOrigineMere());
        patientResult.setOriginePere(patient.getOriginePere());
        patientResult.setRace(patient.getRace());
        patientResult.setProfession(patient.getProfession());
        patientResult.setRegimeAlimentaire(patient.getRegimeAlimentaire());
        patientResult.setSituationMatrimonial(patient.getSituationMatrimonial());
        return patientRepository.save(patientResult);
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findPatientById(id);
    }

    @Override
    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findPatientById(id);
        patient.setActif(false);
        patientRepository.save(patient);
    }
}
