package com.meddjamm.sn.config.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "profil")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profil implements Serializable /* extends AbstractAuditableEntity */ {

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GenerationDeSequence")
    @SequenceGenerator(name = "GenerationDeSequence", sequenceName = "GEN_SEG_GEN", allocationSize = 1)*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
    @Version
    private Long version;*/

    private String code;
    private String libelle;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "actions_par_profil",
            joinColumns = @JoinColumn(name = "profil_uid"),
            inverseJoinColumns = @JoinColumn(name = "action_uid"))
    private Set<Action> action;

    @Column(name = "actif")
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
