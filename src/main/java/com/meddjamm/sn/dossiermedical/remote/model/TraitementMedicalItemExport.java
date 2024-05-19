package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraitementMedicalItemExport {
    private String libelle;
    private String psologie;
    private String nbrePrise;
    private String administrePar;
}
