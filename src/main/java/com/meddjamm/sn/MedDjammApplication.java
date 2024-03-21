package com.meddjamm.sn;

import com.meddjamm.sn.config.entity.Action;
import com.meddjamm.sn.config.entity.Profil;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.remote.model.RegisterRequest;
import com.meddjamm.sn.config.repository.ActionRepository;
import com.meddjamm.sn.config.repository.ProfilRepository;
import com.meddjamm.sn.config.repository.UtilisateurrRepository;
import com.meddjamm.sn.config.service.auth.AuthenticationService;
import com.meddjamm.sn.dossiermedical.repository.PatientRepository;
import com.meddjamm.sn.rh.repository.MaladieRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

import static com.meddjamm.sn.utils.UtilString.genererMatricule;

@SpringBootApplication
//        (exclude = {
//                SecurityAutoConfiguration.class
//        })
@AllArgsConstructor
public class MedDjammApplication implements CommandLineRunner {

    private final MaladieRepository maladieRepository;
    private final PatientRepository patientRepository;
    private final UtilisateurrRepository utilisateurrRepository;
    private final ActionRepository actionRepository;
    private final ProfilRepository profilRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(MedDjammApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Action action1 = new Action("ADD_PAT", "Ajouter/Modifier un patient");
        action1.setCreatedByUser("root");
        Action action2 = new Action("ADD_AG", "Ajouter/Modifier un agent medicale");
        action2.setCreatedByUser("root");
        Action action3 = new Action("LST_PAT", "Lister les patients");
        action3.setCreatedByUser("root");
        Action action4 = new Action("LST_AG", "Lister les agents du service");
        action4.setCreatedByUser("root");
        Action action5 = new Action("ADD_RV", "Ajouter/Modifier un rendez-vous");
        action5.setCreatedByUser("root");
        Action action6 = new Action("ADD_CIR", "Générer un circuit d'un patient");
        action6.setCreatedByUser("root");
        Profil profileAdmin = new Profil("ADMIN", "Administrateur", Set.of(action1, action2, action3, action4, action5, action6), 1);
        profileAdmin.setCreatedByUser("root");
        Profil profileUser = new Profil("USER", "Utilisateur", Set.of(action1, action4, action6), 1);
        profileUser.setCreatedByUser("root");

        actionRepository.saveAll(List.of(action1, action2, action3, action4, action5, action6));
        profilRepository.saveAll(List.of(profileAdmin, profileUser));

        Utilisateur utilisateur = Utilisateur.builder().email("root@test").prenom("root").nom("root").matricule(genererMatricule()).motdepasse(passwordEncoder.encode("root")).profil(profileAdmin).actif(true).build();
        utilisateurrRepository.save(utilisateur);

        var admin = RegisterRequest.builder().firstname("Admin").prenom("Admin").email("admin@mail.com").password("password").profilCode("ADMIN").build();
        System.out.println("Admin token: " + authenticationService.register(admin).getAccessToken());

        var manager = RegisterRequest.builder().firstname("User").prenom("User").email("manager@mail.com").password("password").profilCode("USER").build();
        System.out.println("Manager token: " + authenticationService.register(manager).getAccessToken());
        

    }
}
