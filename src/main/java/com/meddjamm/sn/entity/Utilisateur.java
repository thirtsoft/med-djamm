package com.meddjamm.sn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "codeUtilisateur")
    private String codeUtilisateur;

    @Column(name = "motdepasse")
    private String motdepasse;

    @Column(name = "motdepasseprecedent")
    private String motdepasseprecedent;

    @Column(name = "est_valide")
    private int est_valide;

    @Column(name = "mdpamodifier")
    private int mdpamodifier;

    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    @Column(name = "datecreation")
    private Date dateCreation;

    @Column(name = "datemodpass")
    private Date dateModPass;

    @Column(name = "validite")
    private int resteValidite;

    @Column(name = "profil_id")
    private Long profilId;

    @Column(unique = true)
    private String activation;

    private Long createdBy;

    public boolean isEst_valide() {
        if (est_valide == 1)
            return true;
        else
            return false;
    }

    public void setEst_valide(boolean est_valide) {
        if (est_valide == true)
            this.est_valide = 1;
        else
            this.est_valide = 0;
    }

    public boolean isMdpamodifier() {
        if (mdpamodifier == 1)
            return true;
        else
            return false;
    }

    public void setMdpamodifier(boolean mdpamodifier) {
        if (mdpamodifier == true)
            this.mdpamodifier = 1;
        else
            this.mdpamodifier = 0;
    }
}
