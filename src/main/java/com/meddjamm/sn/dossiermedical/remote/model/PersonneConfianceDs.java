package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonneConfianceDs {

    private Long id;
    private String prenom;
    private String nom;
    private String telephone;
    private String email;
}
