package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.entity.Hospitalisation;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.HospitalisationRepository;
import com.meddjamm.sn.dossiermedical.repository.PatientRepository;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final CircuitPatientRepository circuitPatientRepository;

    private final HospitalisationRepository hospitalisationRepository;

    public PatientServiceImpl(PatientRepository patientRepository,
                              CircuitPatientRepository circuitPatientRepository,
                              HospitalisationRepository hospitalisationRepository) {
        this.patientRepository = patientRepository;
        this.circuitPatientRepository = circuitPatientRepository;
        this.hospitalisationRepository = hospitalisationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) throws Exception {
        String code = patient.getCode();
        Optional<Patient> byCode = patientRepository.findByCode(code);
        if (patient.getId() == null && byCode.isPresent()
                || (patient.getId() != null && byCode.isPresent() && !byCode.get().getId().equals(patient.getId()))) {
            throw new Exception(String.format("L'index %s est déjà associé à un autre patient.", code));
        }
        String telephone = patient.getNumeroTelephone();
        Optional<Patient> byTelephone = patientRepository.findByNumeroTelephone(telephone);
        if (patient.getId() == null && byTelephone.isPresent()
                || (patient.getId() != null && byTelephone.isPresent() && !byTelephone.get().getId().equals(patient.getId()))) {
            throw new Exception(String.format("Le numéro téléphone %s est déjà associé à un autre patient.", telephone));
        }
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
        CircuitPatient circuitPatient = circuitPatientRepository.findCircuitPatientByPatient(patient.getCode());
        circuitPatient.setActif(false);
        circuitPatientRepository.save(circuitPatient);
        Hospitalisation hospitalisation = hospitalisationRepository.findHospitalisationByPatientCode(patient.getCode());
        hospitalisation.setActif(false);
        hospitalisationRepository.saveAndFlush(hospitalisation);
    }

    @Override
    public Page<Patient> findAllPatients(Integer pageNumber, Integer pageSize) {
        return patientRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public long countActivePatient() {
        return patientRepository.countActivePatient();
    }

    @Override
    public List<Patient> findAllActivesPatients() {
        return patientRepository.findAllPatientOrderByFirstName();
    }
}
