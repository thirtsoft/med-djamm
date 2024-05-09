package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.dossiermedical.remote.model.ConsultationMedicalDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientMinDs;
import com.meddjamm.sn.dossiermedical.services.ConsultationMedicalService;
import com.meddjamm.sn.dossiermedical.services.HospitalisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.meddjamm.sn.utils.CSVSupport.DELIMITER;
import static java.util.Arrays.asList;

@Component
@RequiredArgsConstructor
public class CSVExportAssembler {

    private final HospitalisationService hospitalisationService;
    private final HospitalisationAssembler hospitalisationAssembler;

    private final ConsultationMedicalAssembler consultationMedicalAssembler;
    private final ConsultationMedicalService consultationMedicalService;

    public static List<String> headers() {
        return asList(
                "INDEX",
                "DATE INSCRIPTION",
                "PRENOM",
                "NOM",
                "SEXE",
                "TELEPHONE"
        );
    }

    public List<String> mapEntityToList(PatientMinDs patientMinDs) {
        return asList(
                patientMinDs.getCode(),
                String.join(DELIMITER, String.valueOf(patientMinDs.getDateAdmission())),
                String.join(DELIMITER, patientMinDs.getPrenom()),
                String.join(DELIMITER, patientMinDs.getNom()),
                String.join(DELIMITER, String.valueOf(patientMinDs.getDateNaissance())),
                String.join(DELIMITER, String.valueOf(patientMinDs.getTelephone())));
    }

    public static List<String> headeurs() {
        return asList(
                "Index",
                "Date admission",
                "Civilité",
                "Prénom",
                "Nom",
                "Sexe",
                "Situation matrimonail",
                "Adresse",
                "Téléphone",
                "Date consultation",
                "Résumé consultation",
                "Biologique",
                "Immunologique",
                "Imagerie",
                "Anatomopathologie",
                "Numéro hospitalisation",
                "Date hospitalisation",
                "Motif hospitalisation",
                "Histoire maladie",
                "Antécedent médicaux",
                "Antécedent gynécologique",
                "Antécédent familiaux ascendents",
                "Antécedent familiaux collatéraux",
                "Antécédent familiaux descendents",
                "Mode de vie",
                "Examen général",
                "Examen des appareils",
                "FC",
                "FR",
                "PS",
                "PD",
                "Glycémie",
                "SO2",
                "Température",
                "Poids",
                "Taille",
                "Imc",
                "Protocole",
                "Médicaments",
                "Psologie",
                "Nombre prise",
                "Mode administration",
                "Administré",
                "Discussion",
                "Synthèse"
        );
    }

    public List<String> mapEntityToListData(PatientDetailDs patientMinDs) {
        List<ConsultationMedicalDs> consultationMedicalDsList = consultationMedicalAssembler.assembleEntitiesFrom(
                consultationMedicalService.findConsultationMedicalByPatientId(patientMinDs.getCode())
        );
        List<String> stringList = new ArrayList<>();
        stringList.add(patientMinDs.getCode());
        stringList.add(String.join(DELIMITER, String.valueOf(patientMinDs.getDateAdmission())));
        stringList.add(String.join(DELIMITER, patientMinDs.getCivilite()));
        stringList.add(String.join(DELIMITER, patientMinDs.getPrenom()));
        stringList.add(String.join(DELIMITER, patientMinDs.getNom()));
        stringList.add(String.join(DELIMITER, patientMinDs.getSexe()));
        stringList.add(String.join(DELIMITER, patientMinDs.getSituationMatrimonial()));
        stringList.add(String.join(DELIMITER, patientMinDs.getAddress()));
        stringList.add(String.join(DELIMITER, String.valueOf(patientMinDs.getNumeroTelephone())));

/*        List<String> stringListCons = new ArrayList<>();
        for (ConsultationMedicalDs consultationMedicalDs : consultationMedicalDsList) {
            stringListCons.add(String.join(DELIMITER, String.valueOf(consultationMedicalDs.getCreatedDate()))),
                    stringListCons.add(String.join(DELIMITER, consultationMedicalDs.getConsultationDs().getResume())),
                    stringListCons.add(String.join(DELIMITER, consultationMedicalDs.getExamenBiologiqueDs().getBiologie())),
                    stringListCons.add(String.join(DELIMITER, consultationMedicalDs.getExamenBiologiqueDs().getImmunologie())),
                    stringListCons.add(String.join(DELIMITER, consultationMedicalDs.getExamenBiologiqueDs().getImagerie())),
                    stringListCons.add(String.join(DELIMITER, consultationMedicalDs.getExamenBiologiqueDs().getAnatomopathologie()))

        }*/

        return stringList;
        //   stringList
        //     patientMinDs.getCode(),
        //    String.join(DELIMITER, String.valueOf(patientMinDs.getDateAdmission())),
        //    String.join(DELIMITER, patientMinDs.getCivilite()),
        //    String.join(DELIMITER, patientMinDs.getPrenom()),
        //    String.join(DELIMITER, patientMinDs.getNom()),
        //    String.join(DELIMITER, patientMinDs.getSexe()),
        //    String.join(DELIMITER, patientMinDs.getSituationMatrimonial()),
        //    String.join(DELIMITER, patientMinDs.getAddress()),
        //    String.join(DELIMITER, String.valueOf(patientMinDs.getNumeroTelephone())));


    }

}