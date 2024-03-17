package com.meddjamm.sn.config.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "action")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Action extends AbstractAuditableEntity {

    private String code;
    private String libelle;
    @ManyToMany
    private Collection<Profil> profils;

    public Action(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public void removeAllProfilesFromAction() {
        if (this.getProfils()!=null){
            List<Profil> profilsInAction = this.getProfils().stream().toList();
            profilsInAction.forEach(this::removeProfileToAction);
        }
    }

    private void removeProfileToAction(Profil profil) {
        profil.getAction().remove(this);
        this.getProfils().remove(profil);
    }

    public void assignProfileToAction(Profil profil) {
        profil.getAction().add(this);
        this.getProfils().add(profil);
    }
}
