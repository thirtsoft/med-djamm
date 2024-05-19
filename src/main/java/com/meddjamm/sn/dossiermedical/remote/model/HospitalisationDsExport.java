package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalisationDsExport {
    private String biologie;
    private String immunologie;
    private String imagerie;
    private String anatomopathologie;
    private String resume;
    private String observation;
    private List<TraitementMedicalItemExport> traitementMedicalItemExport;
}
