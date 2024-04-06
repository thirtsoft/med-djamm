package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.dossiermedical.remote.model.PatientMinDs;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.meddjamm.sn.utils.CSVSupport.DELIMITER;
import static java.util.Arrays.asList;

@Component
public class PDFExportAssembler {

    private final PatientService patientService;
    private final PatientAssembler patientAssembler;

    public PDFExportAssembler(PatientService patientService, PatientAssembler patientAssembler) {
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
    }

    public static List<String> headers() {
        return asList(
                "INDEX",
                "DATE INSCRIPTION",
                "PRENOM",
                "NOM",
                "SEXE",
                "AGE"
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
}
