package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.dossiermedical.entity.Diagnostic;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.entity.PersonneConfiance;
import com.meddjamm.sn.dossiermedical.remote.model.DiagnosticDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientMinDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientUpdateDs;
import com.meddjamm.sn.dossiermedical.remote.model.PersonneConfianceDs;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientAssembler {

    private final DiagnosticAssembler diagnosticAssembler;

    private final PatientService patientService;

    public PatientAssembler(DiagnosticAssembler diagnosticAssembler,
                            PatientService patientService) {
        this.diagnosticAssembler = diagnosticAssembler;
        this.patientService = patientService;
    }

    public List<PatientMinDs> assembleEntitiesFrom(List<Patient> patients) {
        return patients.stream().map(this::assembleMinFrom).toList();
    }

    public PatientDetailDs assemblePatientDetails(Patient patient) {
        PatientDetailDs patientDetailDs = new PatientDetailDs();
        if (patient.getCode() != null)
            patientDetailDs.setCode(patient.getCode());
        patientDetailDs.setId(patient.getId());
        patientDetailDs.setDateAdmission(patient.getDateAdmission());
        patientDetailDs.setNom(patient.getNom());
        patientDetailDs.setPrenom(patient.getPrenom());
        patientDetailDs.setSexe(patient.getSexe());
        patientDetailDs.setAge(patient.getAge());
        patientDetailDs.setCivilite(patient.getCivilite());
        patientDetailDs.setAddress(patient.getAddress());
        patientDetailDs.setDateNaissance(patient.getDateNaissance());
        patientDetailDs.setNumeroTelephone(patient.getNumeroTelephone());
        patientDetailDs.setProfession(patient.getProfession());
        patientDetailDs.setSituationMatrimonial(patient.getSituationMatrimonial());
        patientDetailDs.setPhoto(patient.getPhoto());
        patientDetailDs.setRace(patient.getRace());
        patientDetailDs.setEthnie(patient.getEthnie());
        patientDetailDs.setOrigine(patient.getOrigine());
        patientDetailDs.setNationalite(patient.getNationalite());
        patientDetailDs.setOriginePere(patient.getOriginePere());
        patientDetailDs.setOrigineMere(patient.getOrigineMere());
        patientDetailDs.setPrototype(patient.getPrototype());
        patientDetailDs.setConsanguinite(patient.getConsanguinite());
        patientDetailDs.setNiveauSocialEconomique(patient.getNiveauSocialEconomique());
        patientDetailDs.setRegimeAlimentaire(patient.getRegimeAlimentaire());
        patientDetailDs.setCreatedBy(patient.getCreatedBy());
        if (patient.getPersonneConfiance() != null)
            patientDetailDs.setPersonneConfianceDs(assembleDsFromEntity(patient.getPersonneConfiance()));
        patientDetailDs.setIsCircuitGenerated(patient.getIsCircuitGenerated());
        patientDetailDs.setEst_accompagne(patient.isEst_accompagne());
        if (patient.getDiagnostic() != null)
            patientDetailDs.setDiagnosticDs(diagnosticAssembler.assembleEntityToDs(patient.getDiagnostic()));
        return patientDetailDs;
    }

    public Patient assemblePatientFromDs(PatientDetailDs patientDetailDs) {
        Patient patient = new Patient();
        patient.setCode(patientDetailDs.getCode());
        patient.setDateAdmission(patientDetailDs.getDateAdmission());
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
        if (patientDetailDs.getPersonneConfianceDs() != null)
            patient.setPersonneConfiance(assembleEntityFromDs(patientDetailDs.getPersonneConfianceDs()));
        patient.setIsCircuitGenerated(patientDetailDs.getIsCircuitGenerated());
        patient.setCreatedBy(patientDetailDs.getCreatedBy());
        patient.setEst_accompagne(patientDetailDs.isEst_accompagne());
        if (patientDetailDs.getDiagnosticDs() != null)
            patient.setDiagnostic(assembleDiagnosticEntityFromDiagnosticDs(patientDetailDs.getDiagnosticDs()));
        return patient;
    }

    public Patient assembleUpdatePatientFromDs(PatientDetailDs patientDetailDs) {
        Patient patient = patientService.findById(patientDetailDs.getId());
        patient.setCode(patientDetailDs.getCode());
        patient.setDateAdmission(patientDetailDs.getDateAdmission());
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
        patient.setNationalite(patientDetailDs.getNationalite());
        if (patientDetailDs.getPersonneConfianceDs() != null)
            patient.setPersonneConfiance(assembleUpdateEntityFromDs(patient.getPersonneConfiance(), patientDetailDs.getPersonneConfianceDs()));
        patient.setEst_accompagne(patientDetailDs.isEst_accompagne());
        return patient;
    }

    public PatientMinDs assembleMinFrom(Patient patient) {
        PatientMinDs patientMinDs = new PatientMinDs();
        patientMinDs.setId(patient.getId());
        patientMinDs.setCode(patient.getCode());
        patientMinDs.setDateAdmission(patient.getDateAdmission());
        patientMinDs.setNom(patient.getNom());
        patientMinDs.setPrenom(patient.getPrenom());
        patientMinDs.setTelephone(patient.getNumeroTelephone());
        patientMinDs.setDateNaissance(patient.getDateNaissance());
        patientMinDs.setIsCircuitGenerated(patient.getIsCircuitGenerated());
        patientMinDs.setCreatedBy(patient.getCreatedBy());
        patientMinDs.setEst_accompagne(patient.isEst_accompagne());
        patientMinDs.setDiagnosticDs(diagnosticAssembler.assembleEntityToDs(patient.getDiagnostic()));
        return patientMinDs;
    }
    //

    public PersonneConfianceDs assembleDsFromEntity(PersonneConfiance personneConfiance) {
        PersonneConfianceDs personneConfianceDs = new PersonneConfianceDs();
        if (personneConfiance.getId() != null)
            personneConfianceDs.setId(personneConfiance.getId());
        personneConfianceDs.setNom(personneConfiance.getNom());
        personneConfianceDs.setPrenom(personneConfiance.getPrenom());
        personneConfianceDs.setTelephone(personneConfiance.getTelephone());
        personneConfianceDs.setEmail(personneConfiance.getEmail());
        return personneConfianceDs;
    }

    public PersonneConfiance assembleEntityFromDs(PersonneConfianceDs personneConfianceDs) {
        PersonneConfiance personneConfiance = new PersonneConfiance();
        if (personneConfianceDs.getId() != null)
            personneConfiance.setId(personneConfianceDs.getId());
        personneConfiance.setNom(personneConfianceDs.getNom());
        personneConfiance.setPrenom(personneConfianceDs.getPrenom());
        personneConfiance.setTelephone(personneConfianceDs.getTelephone());
        personneConfiance.setEmail(personneConfianceDs.getEmail());
        return personneConfiance;
    }

    public PersonneConfiance assembleUpdateEntityFromDs(PersonneConfiance personneConfiance, PersonneConfianceDs personneConfianceDs) {
        personneConfiance.setNom(personneConfianceDs.getNom());
        personneConfiance.setPrenom(personneConfianceDs.getPrenom());
        personneConfiance.setTelephone(personneConfianceDs.getTelephone());
        personneConfiance.setEmail(personneConfianceDs.getEmail());
        return personneConfiance;
    }

    public Patient assembleUpdatePatient(PatientUpdateDs patientUpdateDs) {
        Patient patient = patientService.findById(patientUpdateDs.getId());
        patient.setPrenom(patientUpdateDs.getPrenom());
        patient.setNom(patientUpdateDs.getNom());
        patient.setSexe(patientUpdateDs.getSexe());
        patient.setCivilite(patientUpdateDs.getCivilite());
        patient.setProfession(patientUpdateDs.getProfession());
        patient.setSituationMatrimonial(patientUpdateDs.getSituationMatrimonial());
        patient.setRace(patientUpdateDs.getRace());
        patient.setEthnie(patientUpdateDs.getEthnie());
        patient.setOrigine(patientUpdateDs.getOrigine());
        patient.setOrigineMere(patientUpdateDs.getOrigineMere());
        patient.setOriginePere(patientUpdateDs.getOriginePere());
        patient.setPrototype(patientUpdateDs.getPrototype());
        patient.setConsanguinite(patientUpdateDs.getConsanguinite());
        patient.setNiveauSocialEconomique(patientUpdateDs.getNiveauSocialEconomique());
        patient.setRegimeAlimentaire(patientUpdateDs.getRegimeAlimentaire());
        if (patientUpdateDs.getDiagnosticDs() != null)
            patient.setDiagnostic(diagnosticAssembler.assembleUpdateDiagnosticFromDs(patient.getDiagnostic(), patientUpdateDs.getDiagnosticDs()));
        return patient;
    }

    public Diagnostic assembleDiagnosticEntityFromDiagnosticDs(DiagnosticDs diagnosticDs) {
        Diagnostic diagnostic = new Diagnostic();
        if (diagnosticDs.getId() != null)
            diagnostic.setId(diagnosticDs.getId());
        diagnostic.setDiagnostic_principal(diagnosticDs.getDiagnostic_principal());
        diagnostic.setDiagnostic_associe(diagnosticDs.getDiagnostic_associe());
        return diagnostic;
    }
}