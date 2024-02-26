package com.meddjamm.sn.rh.entity;

import com.meddjamm.sn.entity.Utilisateur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "agent_medical")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentMedical implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "agent_id")
    private Long id;

    @Column(name = "code_agent")
    private String codeAgent;

    private String civilite;

    private String sexe;

    private String telephone;

    private String portable;

    private String fonction;

    @Column(name = "date_recrutement")
    private Date dateRecrutement;

    private int actif;

    @Column(name = "specialite_uid", nullable = true)
    private Long speciality;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "utilisateur_uid", referencedColumnName = "id_user", nullable = false)
    private Utilisateur utilisateur;

    private int affecte;

    public void setAffecte(boolean affecte) {
        if (affecte == true)
            this.affecte = 1;
        else
            this.affecte = 0;
    }

    public boolean isAffecte() {
        if (affecte == 1)
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

    public boolean isActif() {
        if (actif == 1)
            return true;
        else
            return false;
    }
}
