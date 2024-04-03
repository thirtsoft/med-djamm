package com.meddjamm.sn;

import com.meddjamm.sn.config.repository.ActionRepository;
import com.meddjamm.sn.config.repository.ProfilRepository;
import com.meddjamm.sn.config.repository.UtilisateurRepository;
import com.meddjamm.sn.config.service.auth.AuthenticationService;
import com.meddjamm.sn.dossiermedical.repository.PatientRepository;
import com.meddjamm.sn.rh.repository.MaladieRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//        (exclude = {
//                SecurityAutoConfiguration.class
//        })
@AllArgsConstructor
public class MedDjammApplication implements CommandLineRunner {

    private final MaladieRepository maladieRepository;
    private final PatientRepository patientRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ActionRepository actionRepository;
    private final ProfilRepository profilRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(MedDjammApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

/*
        Action action1 = new Action("ADD_PAT", "Ajouter/Modifier un patient");
        Action action2 = new Action("ADD_AG", "Ajouter/Modifier un agent medicale");
        Action action3 = new Action("LST_PAT", "Lister les patients");
        Action action4 = new Action("LST_AG", "Lister les agents du service");
        Action action5 = new Action("ADD_RV", "Ajouter/Modifier un rendez-vous");
        Action action6 = new Action("ADD_CIR", "Générer un circuit d'un patient");
        Profil profileAdmin = new Profil("ADMIN", "Administrateur", Set.of(action1, action2, action3, action4, action5, action6), 1);
        Profil profileUser = new Profil("USER", "Utilisateur", Set.of(action1, action4, action6), 1);

        actionRepository.saveAll(List.of(action1, action2, action3, action4, action5, action6));
        profilRepository.saveAll(List.of(profileAdmin, profileUser));

        Utilisateur utilisateur = Utilisateur.builder().email("root@test").prenom("root").nom("root").matricule(genererMatricule()).motdepasse(passwordEncoder.encode("root")).profil(profileAdmin).actif(true).build();
        utilisateurRepository.save(utilisateur);

        var admin = RegisterRequest.builder().firstname("Admin").prenom("Admin").email("admin@mail.com").password("password").profilCode("ADMIN").build();
        System.out.println("Admin token: " + authenticationService.register(admin).getAccessToken());

        var manager = RegisterRequest.builder().firstname("User").prenom("User").email("manager@mail.com").password("password").profilCode("USER").build();
        System.out.println("Manager token: " + authenticationService.register(manager).getAccessToken());*/

    }
}
