package com.meddjamm.sn.dossiermedical.remote.model;

import com.meddjamm.sn.rh.remote.model.MedicamentDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraitementMedicalItemDs {

    private Long id;

    private String code;

    private String psologie;

    private String nbrePrise;

    private MedicamentDs medicamentDs;

    private String administrePar;

    private int est_administre;
}
