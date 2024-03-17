package com.meddjamm.sn.config.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode
@Entity
@Table(name = "profil")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profil extends AbstractAuditableEntity {

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
