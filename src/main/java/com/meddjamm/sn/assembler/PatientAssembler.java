package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.Patient;
import com.meddjamm.sn.remote.model.PatientDetailDs;
import com.meddjamm.sn.remote.model.PatientMinDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientAssembler {
    public List<PatientMinDs> assembleEntitiesFrom(List<Patient> patients) {
        return patients.stream().map(this::assembleMinFrom).toList();
    }

    public PatientDetailDs assemblePatientDetails(Patient patient) {
        PatientDetailDs PatientDetailDs = new PatientDetailDs();
        PatientDetailDs.setIndex(patient.getIndex());
        PatientDetailDs.setDateInscription(patient.getDateInscription());
        PatientDetailDs.setNom(patient.getNom());
        PatientDetailDs.setPrenom(patient.getPrenom());
        PatientDetailDs.setSexe(patient.getSexe());
        PatientDetailDs.setAge(patient.getAge());
        PatientDetailDs.setCivilite(patient.getCivilite());
        PatientDetailDs.setAddress(patient.getAddress());
        PatientDetailDs.setDateNaissance(patient.getDateNaissance());
        PatientDetailDs.setNumeroTelephone(patient.getNumeroTelephone());
        PatientDetailDs.setProfession(patient.getProfession());
        PatientDetailDs.setSituationMatrimonial(patient.getSituationMatrimonial());
        PatientDetailDs.setPhoto(patient.getPhoto());
        PatientDetailDs.setRace(patient.getRace());
        PatientDetailDs.setEthnie(patient.getEthnie());
        PatientDetailDs.setOrigine(patient.getOrigine());
        PatientDetailDs.setNationalite(patient.getNationalite());
        PatientDetailDs.setOriginePere(patient.getOriginePere());
        PatientDetailDs.setOrigineMere(patient.getOrigineMere());
        PatientDetailDs.setPrototype(patient.getPrototype());
        PatientDetailDs.setConsanguinite(patient.getConsanguinite());
        PatientDetailDs.setNiveauSocialEconomique(patient.getNiveauSocialEconomique());
        PatientDetailDs.setRegimeAlimentaire(patient.getRegimeAlimentaire());
        return PatientDetailDs;
    }

    public Patient assemblePatientFromDs(PatientDetailDs patientDetailDs) {
        Patient patient = new Patient();
        patient.setIndex(patientDetailDs.getIndex());
        patient.setDateInscription(patientDetailDs.getDateInscription());
        patient.setNom(patientDetailDs.getNom());
        patient.setPrenom(patientDetailDs.getPrenom());
        patient.setSexe(patientDetailDs.getSexe());
        patient.setAge(patientDetailDs.getAge());
        patient.setCivilite(patientDetailDs.getCivilite());
        patient.setAddress(patientDetailDs.getAddress());
        patient.setDateNaissance(patientDetailDs.getDateNaissance());
        patient.setNumeroTelephone(patientDetailDs.getNumeroTelephone());
        patient.setProfession(patientDetailDs.getProfession());
        patient.setSituationMatrimonial(patientDetailDs.getSituationMatrimonial());
        patient.setPhoto(patientDetailDs.getPhoto());
        patient.setRace(patientDetailDs.getRace());
        patient.setEthnie(patientDetailDs.getEthnie());
        patient.setOrigine(patientDetailDs.getOrigine());
        patient.setNationalite(patientDetailDs.getNationalite());
        patient.setOriginePere(patientDetailDs.getOriginePere());
        patient.setOrigineMere(patientDetailDs.getOrigineMere());
        patient.setPrototype(patientDetailDs.getPrototype());
        patient.setConsanguinite(patientDetailDs.getConsanguinite());
        patient.setNiveauSocialEconomique(patientDetailDs.getNiveauSocialEconomique());
        patient.setRegimeAlimentaire(patientDetailDs.getRegimeAlimentaire());
        return patient;
    }

    public PatientMinDs assembleMinFrom(Patient patient) {
        PatientMinDs patientMinDs = new PatientMinDs();
        patientMinDs.setIndex(patient.getIndex());
        patientMinDs.setDateInscription(patient.getDateInscription());
        patientMinDs.setNom(patient.getNom());
        patientMinDs.setPrenom(patient.getPrenom());
        patientMinDs.setSexe(patient.getSexe());
        patientMinDs.setAge(patient.getAge());
        return patientMinDs;
    }
}