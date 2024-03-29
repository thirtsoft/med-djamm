package com.meddjamm.sn.assembler;

import com.meddjamm.sn.dossiermedical.assembler.PatientAssembler;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import com.meddjamm.sn.entity.Hospitalisation;
import com.meddjamm.sn.remote.model.HospitalisationDetailDs;
import com.meddjamm.sn.remote.model.HospitalisationDs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HospitalisationAssembler {

    private final DocumentAssembler documentAssembler;
    private final PatientService patientService;
    private final PatientAssembler patientAssembler;

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
        return hospitalisationDs;
    }
}
