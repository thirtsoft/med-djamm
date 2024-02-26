package com.meddjamm.sn.assembler;

import com.meddjamm.sn.dossiermedical.assembler.PatientAssembler;
import com.meddjamm.sn.entity.Consultation;
import com.meddjamm.sn.remote.model.ConsultationDetailDs;
import com.meddjamm.sn.remote.model.ConsultationDs;
import com.meddjamm.sn.remote.model.MedecinDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.services.MedecinService;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultationAssembler {

    private final DocumentAssembler documentAssembler;

    private final MedecinService medecinService;

    private final MedecinAssembler medecinAssembler;

    private final PatientService patientService;

    private final PatientAssembler patientAssembler;

    public ConsultationAssembler(DocumentAssembler documentAssembler,
                                 MedecinService medecinService,
                                 MedecinAssembler medecinAssembler,
                                 PatientService patientService,
                                 PatientAssembler patientAssembler) {
        this.documentAssembler = documentAssembler;
        this.medecinService = medecinService;
        this.medecinAssembler = medecinAssembler;
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
    }

    public List<ConsultationDetailDs> assembleEntitiesFrom(List<Consultation> consultations) {
        return consultations.stream().map(this::assembleConsultationToDs).toList();
    }

    public ConsultationDs assembleEntityToDs(Consultation consultation) {
        ConsultationDs consultationDs = new ConsultationDs();
        consultationDs.setId(consultation.getId());
        consultationDs.setIndexPatient(consultation.getIndexPatient());
        consultationDs.setMatricule(consultation.getMatricule());
        consultationDs.setPassageId(consultation.getPassagePatientId());
        consultationDs.setResume(consultation.getResume());
        consultationDs.setActif(consultation.isActif());
        consultationDs.setCreatedDate(consultation.getCreatedDate());
        consultationDs.setDocumentDs(documentAssembler.createListDocumentDs(
                consultation.getDocuments()
        ));
        return consultationDs;
    }

    public Consultation assembleConsultationFromDs(ConsultationDs consultationDs) {
        Consultation consultation = new Consultation();
        consultation.setId(consultationDs.getId());
        consultation.setIndexPatient(consultationDs.getIndexPatient());
        consultation.setMatricule(consultationDs.getMatricule());
        consultation.setPassagePatientId(consultationDs.getPassageId());
        consultation.setResume(consultationDs.getResume());
        consultation.setActif(consultationDs.isActif());
        consultation.setCreatedDate(consultationDs.getCreatedDate());
        consultation.setDocuments(documentAssembler.createSetDocuments(
                consultationDs.getDocumentDs()
        ));
        return consultation;
    }

    public ConsultationDetailDs assembleConsultationToDs(Consultation consultation) {
        ConsultationDetailDs consultationDs = new ConsultationDetailDs();
        consultationDs.setId(consultation.getId());
        consultationDs.setActif(consultation.isActif());
        consultationDs.setResume(consultation.getResume());
        consultationDs.setCreatedDate(consultation.getCreatedDate());
        consultationDs.setDocumentDs(documentAssembler.createListDocumentDs(
                consultation.getDocuments()
        ));
        if (consultation.getMatricule() != null) {
            MedecinDetailDs medecinDetailDs = medecinAssembler
                    .assembleEntitiesToDs(medecinService.findByMatricule(consultation.getMatricule()));
            consultationDs.setMedecinDetailDs(medecinDetailDs);
        }
        if (consultation.getIndexPatient() != null) {
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(
                    patientService.findByCode(consultation.getIndexPatient())
            );
            consultationDs.setPatientDetailDs(patientDetailDs);
        }
        return consultationDs;
    }
}
