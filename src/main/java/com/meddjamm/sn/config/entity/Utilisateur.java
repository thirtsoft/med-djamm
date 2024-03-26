package com.meddjamm.sn.config.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur extends AbstractAuditableEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    @SequenceGenerator(
            name = "MAT_SEG_GEN",
            sequenceName = "matriculeGenerator"
    )
    @GeneratedValue(generator = "matriculeGenerator", strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private String matricule;

    @Column(name = "codeUtilisateur")
    private String codeUtilisateur;
    @Column(name = "motdepasse")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=]).{8,}$")
    private String motdepasse;
    @Column(name = "motdepasseprecedent")
    private String motdepasseprecedent;
    @Column(name = "est_valide")
    private int est_valide;
    @Column(name = "mdpamodifier")
    private int mdpamodifier;
    private String nom;
    private String prenom;
    @Column(unique = true)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    private String telephone;
    @Column(name = "datemodpass")
    private Date dateModPass;
    @Column(name = "validite")
    private int resteValidite;
    @Column(name = "profil_id")
    private Long profilId;

    private String sexe;

    private String civilite;

    private String fonction;

    private String adresse;

    private String typeUtilisateur;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_uid")
    private Profil profil;
    private boolean actif;

    @Column(unique = true)
    private String activation;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getProfil()
                .getAction().stream()
                .map(Action::getLibelle)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.motdepasse;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.actif;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isEnabled() {
        return this.actif;
    }
}
