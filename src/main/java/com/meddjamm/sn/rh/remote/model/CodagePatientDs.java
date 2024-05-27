package com.meddjamm.sn.rh.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodagePatientDs {

    private Long id;

    private Long patientId;

    private List<String> epidemiologiques;

    private List<String> cliniques;

    private List<String> examenComplementaires;

    private List<String> traitements;

    private List<String> syntheses;

    private int actif;

    public boolean isActif() {
        if (actif == 1)
            return true;
        else
            return false;
    }

    public void setActif(boolean actif) {
        if (actif == true)
            this.actif = 1;
        else
            this.actif = 0;
    }
}
