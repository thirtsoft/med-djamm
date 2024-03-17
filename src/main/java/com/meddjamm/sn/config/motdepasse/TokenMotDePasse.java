package com.meddjamm.sn.config.motdepasse;

import com.meddjamm.sn.config.entity.AbstractEntity;
import com.meddjamm.sn.config.entity.Utilisateur;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TokenMotDePasse extends AbstractEntity {
    private String token;
    private Date dateExpiration;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;

    public TokenMotDePasse(String token, Utilisateur utilisateur) {
        this.token = token;
        this.utilisateur = utilisateur;
        this.dateExpiration = this.getTokenExpiration();
    }

    public TokenMotDePasse(String token) {
        this.token = token;
        this.dateExpiration = this.getTokenExpiration();
    }

    public Date getTokenExpiration() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, 10);
        return new Date(calendar.getTime().getTime());
    }
}