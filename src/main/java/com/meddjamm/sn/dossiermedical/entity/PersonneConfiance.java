package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "personne_confiance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonneConfiance extends AbstractAuditableEntity implements Serializable {

    private String prenom;
    private String nom;
    private String telephone;
    private String email;
}
