package com.meddjamm.sn.assembler;

import com.meddjamm.sn.remote.model.PatientMinDs;
import com.meddjamm.sn.services.PatientService;
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
                patientMinDs.getIndex(),
                String.join(DELIMITER, String.valueOf(patientMinDs.getDateInscription())),
                String.join(DELIMITER, patientMinDs.getPrenom()),
                String.join(DELIMITER, patientMinDs.getNom()),
                String.join(DELIMITER, patientMinDs.getSexe()),
                String.join(DELIMITER, String.valueOf(patientMinDs.getAge())));
    }
}
