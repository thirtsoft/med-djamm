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
import com.meddjamm.sn.repository.ClassificationRepository;
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
    private final ClassificationRepository classificationRepository;
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

        Action action1 = new Action("aujout", "aujout");
        action1.setCreatedByUser("root");
        Action action2 = new Action("modifier", "modifier");
        action2.setCreatedByUser("root");
        Action action3 = new Action("supprimer", "supprimer");
        action3.setCreatedByUser("root");
        Action action4 = new Action("consulter", "consulter");
        action4.setCreatedByUser("root");
        Action action5 = new Action("envoyer", "envoyer");
        action5.setCreatedByUser("root");
        Action action6 = new Action("annuler", "annuler");
        action6.setCreatedByUser("root");
        Profil profileAdmin = new Profil("ADMIN", "Administrateur", Set.of(action1, action2, action3, action4, action5, action6), 1);
        profileAdmin.setCreatedByUser("root");
        Profil profileUser = new Profil("USER", "Utilisateur", Set.of(action1, action4, action6), 1);
        profileUser.setCreatedByUser("root");

        actionRepository.saveAll(List.of(action1, action2, action3, action4, action5, action6));
        profilRepository.saveAll(List.of(profileAdmin, profileUser));

        Utilisateur utilisateur = Utilisateur.builder()
                .email("root@test")
                .prenom("root")
                .nom("root")
                .matricule(genererMatricule())
                .motdepasse(passwordEncoder.encode("root"))
                .profil(profileAdmin)
                .actif(true)
                .build();
        utilisateurrRepository.save(utilisateur);

        var admin = RegisterRequest.builder()
                .firstname("Admin")
                .prenom("Admin")
                .email("admin@mail.com")
                .password("password")
                .profilCode("ADMIN")
                .build();
        System.out.println("Admin token: " + authenticationService.register(admin).getAccessToken());

        var manager = RegisterRequest.builder()
                .firstname("User")
                .prenom("User")
                .email("manager@mail.com")
                .password("password")
                .profilCode("USER")
                .build();
        System.out.println("Manager token: " + authenticationService.register(manager).getAccessToken());
//
/*

        //Classification cl1 = classificationRepository.save(new Classification(1L, "PCR", new Date(), 1));
        Classification cl2 = classificationRepository.save(new Classification(2L, "POR", new Date(), 1));
        Classification cl3 = classificationRepository.save(new Classification(3L, "ABC", new Date(), 1));
        Classification cl4 = classificationRepository.save(new Classification(4L, "ABBA", new Date(), 1));
        Classification cl5 = classificationRepository.save(new Classification(5L, "FANA", new Date(), 1));

        Maladie m1 = maladieRepository.save(new Maladie(1L, "Paludisme", new Date(), 1));
        Maladie m2 = maladieRepository.save(new Maladie(2L, "Diarrhe", new Date(), 1));
        Maladie m3 = maladieRepository.save(new Maladie(3L, "Mot de tete", new Date(), 1));
        Maladie m4 = maladieRepository.save(new Maladie(4L, "Bas ventre", new Date(), 1));
*/
//        Patient p1 = patientRepository.save(new Patient("1L", "Tairou", "Diallo", "Mr", "Mariste", "779440310", 1));
//        Patient p2 = patientRepository.save(new Patient("2L", "Saliou woury", "Diallo", "Mr", "Mariste", "779440310", 1));
//        Patient p3 = patientRepository.save(new Patient("3L", "Binta", "Diallo", "Me", "Mariste", "779440310", 1));
//        Patient p4 = patientRepository.save(new Patient("4L", "Aicha", "Diallo", "Me", "Mariste", "779440310", 1));
//        Patient p5 = patientRepository.save(new Patient("5L", "Lama", "Diallo", "Mr", "Mariste", "779440310", 1));


    }
}
