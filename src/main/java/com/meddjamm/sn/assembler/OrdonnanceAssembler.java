package com.meddjamm.sn.assembler;


import com.meddjamm.sn.dossiermedical.assembler.OrdonnanceItemAssembler;
import com.meddjamm.sn.dossiermedical.assembler.PatientAssembler;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.entity.Ordonnance;
import com.meddjamm.sn.remote.model.*;
import com.meddjamm.sn.rh.assembler.MedicamentAssembler;
import com.meddjamm.sn.rh.remote.model.MedicamentDs;
import com.meddjamm.sn.services.MedecinService;
import com.meddjamm.sn.rh.services.MedicamentService;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdonnanceAssembler {

    private final MedicamentService medicamentService;
    private final MedicamentAssembler medicamentAssembler;
    private final PatientService patientService;
    private final PatientAssembler patientAssembler;
    private final MedecinService medecinService;
    private final MedecinAssembler medecinAssembler;
    private final OrdonnanceItemAssembler ordonnanceItemAssembler;

    public OrdonnanceAssembler(MedicamentService medicamentService,
                               MedicamentAssembler medicamentAssembler,
                               PatientService patientService,
                               PatientAssembler patientAssembler,
                               MedecinService medecinService,
                               MedecinAssembler medecinAssembler,
                               OrdonnanceItemAssembler ordonnanceItemAssembler) {
        this.medicamentService = medicamentService;
        this.medicamentAssembler = medicamentAssembler;
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
        this.medecinService = medecinService;
        this.medecinAssembler = medecinAssembler;
        this.ordonnanceItemAssembler = ordonnanceItemAssembler;
    }

    public List<OrdonnanceDetailDs> assembleEntitiesFrom(List<Ordonnance> ordonnances) {
        return ordonnances.stream().map(this::assembleEntitiesToDs).toList();
    }

    public OrdonnanceDs assembleEntityToDs(Ordonnance ordonnance) {
        OrdonnanceDs ordonnanceDs = new OrdonnanceDs();
        ordonnanceDs.setId(ordonnance.getId());
        ordonnanceDs.setCode(ordonnance.getCode());
        ordonnanceDs.setIndexPatient(ordonnance.getIndexPatient());
        ordonnanceDs.setMatricule(ordonnance.getMatricule());
        ordonnanceDs.setPsologie(ordonnance.getPsologie());
        ordonnanceDs.setNbrePrise(ordonnance.getNbrePrise());
        ordonnanceDs.setOrdonnanceItemDs(ordonnanceItemAssembler.createListOrdonnanceItemDs(ordonnance.getOrdonnanceItems()));
        ordonnanceDs.setCreatedDate(ordonnance.getCreatedDate());
        ordonnanceDs.setActif(ordonnance.isActif());
        return ordonnanceDs;
    }

    public Ordonnance assembleOrdonnanceFromDs(OrdonnanceDs ordonnanceDs) {
        Ordonnance ordonnance = new Ordonnance();
        ordonnance.setId(ordonnanceDs.getId());
        ordonnance.setCode(ordonnanceDs.getCode());
        ordonnance.setIndexPatient(ordonnanceDs.getIndexPatient());
        ordonnance.setMatricule(ordonnanceDs.getMatricule());
        ordonnance.setPsologie(ordonnanceDs.getPsologie());
        ordonnance.setNbrePrise(ordonnanceDs.getNbrePrise());
        ordonnance.setOrdonnanceItems(ordonnanceItemAssembler.createSetOrdonnanceItem(ordonnanceDs.getOrdonnanceItemDs()));
        ordonnance.setCreatedDate(ordonnanceDs.getCreatedDate());
        ordonnance.setActif(ordonnanceDs.isActif());
        return ordonnance;
    }
    
    public OrdonnanceDetailDs assembleEntitiesToDs(Ordonnance ordonnance) {
        OrdonnanceDetailDs ordonnanceDs = new OrdonnanceDetailDs();
        ordonnanceDs.setId(ordonnance.getId());
        if (ordonnance.getCode() != null) {
            MedicamentDs medicamentDs = medicamentAssembler.assembleEntityToDs(
                    medicamentService.findByCode(ordonnance.getCode())
            );
            ordonnanceDs.setMedicamentDs(medicamentDs);
        }
        if (ordonnance.getIndexPatient() != null) {
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(
                    patientService.findByCode(ordonnance.getIndexPatient())
            );
            ordonnanceDs.setPatientDetailDs(patientDetailDs);
        }
        if (ordonnance.getMatricule() != null) {
            MedecinDetailDs medecinDetailDs = medecinAssembler.assembleEntitiesToDs(
                    medecinService.findByMatricule(ordonnance.getMatricule())
            );
            ordonnanceDs.setMedecinDetailDs(medecinDetailDs);
        }
        ordonnanceDs.setPsologie(ordonnance.getPsologie());
        ordonnanceDs.setNbrePrise(ordonnance.getNbrePrise());
        ordonnanceDs.setOrdonnanceItemDs(ordonnanceItemAssembler.createListOrdonnanceItemDs(ordonnance.getOrdonnanceItems()));
        ordonnanceDs.setCreatedDate(ordonnance.getCreatedDate());
        ordonnanceDs.setActif(ordonnance.isActif());
        return ordonnanceDs;
    }
}
