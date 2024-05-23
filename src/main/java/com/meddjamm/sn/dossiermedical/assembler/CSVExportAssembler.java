package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.dossiermedical.remote.model.DiagnosticDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailsExport;
import com.meddjamm.sn.dossiermedical.remote.model.PatientMinDs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.meddjamm.sn.utils.CSVSupport.DELIMITER;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class CSVExportAssembler {

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
                String.join(DELIMITER, valueOf(patientMinDs.getDateAdmission())),
                String.join(DELIMITER, patientMinDs.getPrenom()),
                String.join(DELIMITER, patientMinDs.getNom()),
                String.join(DELIMITER, valueOf(patientMinDs.getDateNaissance())),
                String.join(DELIMITER, valueOf(patientMinDs.getTelephone())));
    }

    public static List<String> headeurs() {
        return asList(
                "INDEX",
                "NOM",
                "PRÉNOM",
                "DATE ADMISSION",
                "ADRESSE",
                "TÉLÉPHONE",
                "BIOLOGIQUE",
                "IMMUNOLOGIQUE",
                "IMAGERIE",
                "ANATOMOPATHOLOGIE",
                "DISCUSSION",
                "SYNTHÈSE",
                "MÉDICAMENTS",
                "PSOLOGIE",
                "NOMBRE PRISE",
                "MODE ADMINISTRATION"
        );
    }

    public List<String> mapEntityToListData(PatientDetailsExport patientMinDs) {
        List<String> stringList = new ArrayList<>();
        stringList.add(patientMinDs.getCode());
        stringList.add(String.join(DELIMITER, patientMinDs.getNom()));
        stringList.add(String.join(DELIMITER, patientMinDs.getPrenom()));
        stringList.add(String.join(DELIMITER, valueOf(patientMinDs.getDateAdmission())));
        stringList.add(String.join(DELIMITER, patientMinDs.getAddress()));
        stringList.add(String.join(DELIMITER, patientMinDs.getNumeroTelephone()));

        patientMinDs.getHospitalisation().forEach(consultationMedicalDs -> {
            stringList.add(String.join(DELIMITER, consultationMedicalDs.getBiologie()));
            stringList.add(String.join(DELIMITER, consultationMedicalDs.getImmunologie()));
            stringList.add(String.join(DELIMITER, consultationMedicalDs.getImagerie()));
            stringList.add(String.join(DELIMITER, consultationMedicalDs.getAnatomopathologie()));
            stringList.add(String.join(DELIMITER, consultationMedicalDs.getResume()));
            stringList.add(String.join(DELIMITER, consultationMedicalDs.getObservation()));
            consultationMedicalDs.getTraitementMedicalItemExport().forEach(traitementMedicalItemExport -> {
                stringList.add(String.join(DELIMITER, traitementMedicalItemExport.getLibelle()));
                stringList.add(String.join(DELIMITER, traitementMedicalItemExport.getPsologie()));
                stringList.add(String.join(DELIMITER, traitementMedicalItemExport.getNbrePrise()));
                stringList.add(String.join(DELIMITER, traitementMedicalItemExport.getAdministrePar()));
            });
        });

        return stringList;
    }


    public static List<String> ENTETEPATIENTS() {
        return asList(
                "INDEX",
                "NOM",
                "PRÉNOM",
                "DATE ADMISSION",
                "ADRESSE",
                "TÉLÉPHONE",
                "AGE",
                "RACE",
                "ETHNIE",
                "ORIGINE",
                "NATIONALITE",
                "ORIGINE PERE",
                "ORIGINE MERE",
                "PROTOTYPE",
                "CONSANGUINITE",
                "NIVEAU SOCIO ECONOMIQUE",
                "REGIME ALIMENTAIRE",
                "DIAGNOSTIC PRINCIPAL",
                "DIAGNOSTIC ASSOCIE"
        );
    }

    public List<String> mapEntityToData(PatientDetailDs patientMinDs) {
        List<String> stringList = new ArrayList<>();
        stringList.add(patientMinDs.getCode());
        stringList.add(String.join(DELIMITER, patientMinDs.getNom()));
        stringList.add(String.join(DELIMITER, patientMinDs.getPrenom()));
        stringList.add(String.join(DELIMITER, valueOf(patientMinDs.getDateAdmission())));
        stringList.add(String.join(DELIMITER, patientMinDs.getAddress()));
        stringList.add(String.join(DELIMITER, patientMinDs.getNumeroTelephone()));
        stringList.add(String.join(DELIMITER, valueOf(patientMinDs.getAge())));
        stringList.add(String.join(DELIMITER, patientMinDs.getRace()));
        stringList.add(String.join(DELIMITER, patientMinDs.getEthnie()));
        stringList.add(String.join(DELIMITER, patientMinDs.getOrigine()));
        stringList.add(String.join(DELIMITER, patientMinDs.getNationalite()));
        stringList.add(String.join(DELIMITER, patientMinDs.getOriginePere()));
        stringList.add(String.join(DELIMITER, patientMinDs.getOrigineMere()));
        stringList.add(String.join(DELIMITER, patientMinDs.getPrototype()));
        stringList.add(String.join(DELIMITER, patientMinDs.getConsanguinite()));
        stringList.add(String.join(DELIMITER, patientMinDs.getRegimeAlimentaire()));
        stringList.add(String.join(DELIMITER, ofNullable(patientMinDs.getDiagnosticDs())
                .map(DiagnosticDs::getDiagnostic_principal)
                .orElse(null)));
        stringList.add(String.join(DELIMITER, ofNullable(patientMinDs.getDiagnosticDs())
                .map(DiagnosticDs::getDiagnostic_associe)
                .orElse(null)));

        return stringList;
    }

}