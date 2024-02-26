package com.meddjamm.sn.rh.entity;

import com.meddjamm.sn.rh.entity.Action;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "profil")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String code;

    private String libelle;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "actions_par_profil",
            joinColumns = @JoinColumn(name = "profil_uid"),
            inverseJoinColumns = @JoinColumn(name = "action_uid"))
    private Set<Action> action;

    private Date createdDate;

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
