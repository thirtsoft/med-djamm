package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.assembler.MedecinAssembler;
import com.meddjamm.sn.entity.Medecin;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.entity.RendezVous;
import com.meddjamm.sn.remote.model.MedecinDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.RendezVousDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.RendezVousDs;
import com.meddjamm.sn.services.MedecinService;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import com.meddjamm.sn.utils.Constants;
import org.springframework.stereotype.Component;

@Component
public class RendezVousAssembler {

    private final PatientService patientService;

    private final PatientAssembler patientAssembler;

    private final MedecinService medecinService;

    private final MedecinAssembler medecinAssembler;

    public RendezVousAssembler(PatientService patientService,
                               PatientAssembler patientAssembler,
                               MedecinService medecinService,
                               MedecinAssembler medecinAssembler) {
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
        this.medecinService = medecinService;
        this.medecinAssembler = medecinAssembler;
    }

    public RendezVousDs assembleEntityToDs(RendezVous rendezVous) {
        RendezVousDs rendezVousDs = new RendezVousDs();
        rendezVousDs.setLibelle(rendezVous.getLibelle());
        rendezVousDs.setCode(rendezVous.getCode());
        rendezVousDs.setMatricule(rendezVous.getMatricule());
        rendezVousDs.setCreatedBy(rendezVous.getCreatedBy());
        rendezVousDs.setEtat(rendezVous.getEtat());
        rendezVousDs.setDateRendezVous(rendezVous.getDateRendezVous());
        rendezVousDs.setHeure(rendezVous.getHeure());
        rendezVousDs.setCreateDate(rendezVous.getCreateDate());
        rendezVousDs.setActif(rendezVous.isActif());
        return rendezVousDs;
    }

    public RendezVous assembleRendezVoRendezVoususFromDs(RendezVousDs rendezVousDs) {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setCode(rendezVousDs.getCode());
        rendezVous.setLibelle(rendezVousDs.getLibelle());
        rendezVous.setMatricule(rendezVousDs.getMatricule());
        rendezVous.setCreatedBy(rendezVousDs.getCreatedBy());
        rendezVous.setDateRendezVous(rendezVousDs.getDateRendezVous());
        rendezVous.setHeure(rendezVousDs.getHeure());
        rendezVous.setCreateDate(rendezVousDs.getCreateDate());
        rendezVous.setActif(rendezVousDs.isActif());
        return rendezVous;
    }

    public RendezVousDetailDs assembleEntitiesToDs(RendezVous rendezVous) {
        RendezVousDetailDs rendezVousDs = new RendezVousDetailDs();
        if (rendezVous.getId() != null)
            rendezVousDs.setId(rendezVous.getId());
        rendezVousDs.setCreatedBy(rendezVous.getCreatedBy());
        rendezVousDs.setLibelle(rendezVous.getLibelle());
        rendezVousDs.setDateRendezVous(rendezVous.getDateRendezVous());
        rendezVousDs.setHeure(rendezVous.getHeure());
        rendezVousDs.setCreateDate(rendezVous.getCreateDate());
        rendezVousDs.setActif(rendezVous.isActif());
        rendezVousDs.setCode(rendezVous.getCode());
        if (rendezVous.getCode() != null) {
            Patient patient = patientService.findByCode(rendezVous.getCode());
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(patient);
            rendezVousDs.setPatient(patientDetailDs);
        }
        if (rendezVous.getMatricule() != null) {
            Medecin medecin = medecinService.findByMatricule(rendezVous.getMatricule());
            MedecinDetailDs medecinDetailDs = medecinAssembler.assembleEntitiesToDs(medecin);
            rendezVousDs.setMedecinDetailDs(medecinDetailDs);
        }
        rendezVousDs.setEtat(rendezVous.getEtat());
        rendezVousDs.setLibelleEtat(getLibelleEtat(rendezVous.getEtat()));
        return rendezVousDs;
    }

    public String getLibelleEtat(int etat) {
        if (etat == Constants.ETAT_PROGRAMME)
            return "PROGRAMME";
        else if (etat == Constants.ETAT_ACCEPTE)
            return "ACCEPTE";
        else if (etat == Constants.ETAT_EVAL_PATIENT)
            return "EVALUATION";
        else if (etat == Constants.ETAT_REJETE)
            return "REJETE";
        return "";
    }
}