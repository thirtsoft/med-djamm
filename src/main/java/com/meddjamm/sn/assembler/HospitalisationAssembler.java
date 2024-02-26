package com.meddjamm.sn.assembler;

import com.meddjamm.sn.dossiermedical.assembler.PatientAssembler;
import com.meddjamm.sn.entity.Hospitalisation;
import com.meddjamm.sn.remote.model.HospitalisationDetailDs;
import com.meddjamm.sn.remote.model.HospitalisationDs;
import com.meddjamm.sn.remote.model.MedecinDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.services.MedecinService;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HospitalisationAssembler {

    private final DocumentAssembler documentAssembler;

    private final PatientService patientService;
    private final PatientAssembler patientAssembler;
    private final MedecinService medecinService;
    private final MedecinAssembler medecinAssembler;

    public HospitalisationAssembler(DocumentAssembler documentAssembler,
                                    PatientService patientService,
                                    PatientAssembler patientAssembler,
                                    MedecinService medecinService,
                                    MedecinAssembler medecinAssembler) {
        this.documentAssembler = documentAssembler;
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
        this.medecinService = medecinService;
        this.medecinAssembler = medecinAssembler;
    }

    public List<HospitalisationDetailDs> assembleEntitiesFrom(List<Hospitalisation> hospitalisations) {
        return hospitalisations.stream().map(this::assembleEntitiesToDs).toList();
    }

    public HospitalisationDs assembleEntityToDs(Hospitalisation hospitalisation) {
        HospitalisationDs hospitalisationDs = new HospitalisationDs();
        hospitalisationDs.setId(hospitalisation.getId());
        hospitalisationDs.setIndexPatient(hospitalisation.getIndexPatient());
        hospitalisationDs.setMatricule(hospitalisation.getMatricule());
        hospitalisationDs.setResume(hospitalisation.getResume());
        hospitalisationDs.setActif(hospitalisation.isActif());
        hospitalisationDs.setCreatedDate(hospitalisation.getCreatedDate());
        hospitalisationDs.setDocumentDs(documentAssembler.createListDocumentDs(
                hospitalisation.getDocuments()
        ));
        return hospitalisationDs;
    }

    public Hospitalisation assembleHospitalisationFromDs(HospitalisationDs hospitalisationDs) {
        Hospitalisation hospitalisation = new Hospitalisation();
        hospitalisation.setId(hospitalisationDs.getId());
        hospitalisation.setIndexPatient(hospitalisationDs.getIndexPatient());
        hospitalisation.setMatricule(hospitalisationDs.getMatricule());
        hospitalisation.setResume(hospitalisationDs.getResume());
        hospitalisation.setActif(hospitalisationDs.isActif());
        hospitalisation.setCreatedDate(hospitalisationDs.getCreatedDate());
        hospitalisation.setDocuments(documentAssembler.createSetDocuments(
                hospitalisationDs.getDocumentDs()
        ));
        return hospitalisation;
    }

    public HospitalisationDetailDs assembleEntitiesToDs(Hospitalisation hospitalisation) {
        HospitalisationDetailDs hospitalisationDs = new HospitalisationDetailDs();
        hospitalisationDs.setId(hospitalisation.getId());
        hospitalisationDs.setResume(hospitalisation.getResume());
        hospitalisationDs.setActif(hospitalisation.isActif());
        hospitalisationDs.setCreatedDate(hospitalisation.getCreatedDate());
        hospitalisationDs.setDocumentDs(documentAssembler.createListDocumentDs(
                hospitalisation.getDocuments()
        ));
        if (hospitalisation.getIndexPatient() != null) {
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(
                    patientService.findByCode(hospitalisation.getIndexPatient())
            );
            hospitalisationDs.setPatientDetailDs(patientDetailDs);
        }
        if (hospitalisation.getMatricule() != null) {
            MedecinDetailDs medecinDetailDs = medecinAssembler.assembleEntitiesToDs(
                    medecinService.findByMatricule(hospitalisation.getMatricule())
            );
            hospitalisationDs.setMedecinDetailDs(medecinDetailDs);
        }
        return hospitalisationDs;
    }
}
