package com.meddjamm.sn.dossiermedical.remote.model;

import com.meddjamm.sn.rh.remote.model.MedicamentDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraitementMedicalItemDs {

    private Long id;

    private Long medicamendId;

    private String psologie;

    private String nbrePrise;

    private MedicamentDs medicamentDs;

    private String administrePar;

    private int est_administre;

    private LocalDateTime datePrescription;
}
