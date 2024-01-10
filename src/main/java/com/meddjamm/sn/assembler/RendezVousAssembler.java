package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.Patient;
import com.meddjamm.sn.entity.RendezVous;
import com.meddjamm.sn.remote.model.PatientDetailDs;
import com.meddjamm.sn.remote.model.RendezVousDetailDs;
import com.meddjamm.sn.remote.model.RendezVousDs;
import com.meddjamm.sn.services.PatientService;
import com.meddjamm.sn.utils.Constants;
import org.springframework.stereotype.Component;

@Component
public class RendezVousAssembler {

    private final PatientService patientService;

    private final PatientAssembler patientAssembler;

    public RendezVousAssembler(PatientService patientService, PatientAssembler patientAssembler) {
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
    }

    public RendezVousDs assembleEntityToDs(RendezVous rendezVous) {
        RendezVousDs rendezVousDs = new RendezVousDs();
        rendezVousDs.setNumeroRendezVous(rendezVous.getNumeroRendezVous());
        rendezVousDs.setEtat(rendezVous.getEtat());
        rendezVousDs.setDateRendezVous(rendezVous.getDateRendezVous());
        rendezVousDs.setHeure(rendezVous.getHeure());
        rendezVousDs.setCreateDate(rendezVous.getCreateDate());
        rendezVousDs.setActif(rendezVous.isActif());
        rendezVousDs.setIndex(rendezVous.getIndex());
        return rendezVousDs;
    }

    public RendezVous assembleRendezVoRendezVoususFromDs(RendezVousDs rendezVousDs) {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setIndex(rendezVousDs.getIndex());
        rendezVous.setNumeroRendezVous(rendezVousDs.getNumeroRendezVous());
        rendezVous.setDateRendezVous(rendezVousDs.getDateRendezVous());
        rendezVous.setHeure(rendezVousDs.getHeure());
        rendezVous.setCreateDate(rendezVousDs.getCreateDate());
        rendezVous.setActif(rendezVousDs.isActif());
        return rendezVous;
    }

    public RendezVousDetailDs assembleEntitiesToDs(RendezVous rendezVous) {
        RendezVousDetailDs rendezVousDs = new RendezVousDetailDs();
        rendezVousDs.setId(rendezVous.getId());
        rendezVousDs.setNumeroRendezVous(rendezVous.getNumeroRendezVous());
        rendezVousDs.setDateRendezVous(rendezVous.getDateRendezVous());
        rendezVousDs.setHeure(rendezVous.getHeure());
        rendezVousDs.setCreateDate(rendezVous.getCreateDate());
        rendezVousDs.setActif(rendezVous.isActif());
        rendezVousDs.setIndex(rendezVous.getIndex());
        if(rendezVous.getIndex() != null) {
            Patient patient = patientService.findByIndex(rendezVous.getIndex());
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(patient);
            rendezVousDs.setPatient(patientDetailDs);
        }
        rendezVousDs.setEtat(rendezVous.getEtat());
        rendezVousDs.setLibelleEtat(getLibelleEtat(rendezVous.getEtat()));
        return rendezVousDs;
    }

    public String getLibelleEtat(int etat) {
        if(etat == Constants.ETAT_PROGRAMME)
            return "PROGRAMME";
        else if(etat == Constants.ETAT_ACCEPTE)
            return "ACCEPTE";
        else if(etat == Constants.ETAT_EVAL_PATIENT)
            return "EVALUATION";
        else if(etat == Constants.ETAT_REJETE)
            return "REJETE";
        return "";
    }
}
