package com.meddjamm.sn;

import com.meddjamm.sn.config.repository.ActionRepository;
import com.meddjamm.sn.config.repository.ProfilRepository;
import com.meddjamm.sn.config.repository.UtilisateurRepository;
import com.meddjamm.sn.config.service.auth.AuthenticationService;
import com.meddjamm.sn.dossiermedical.repository.PatientRepository;
import com.meddjamm.sn.rh.repository.TypeDocumentRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final PatientRepository patientRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ActionRepository actionRepository;
    private final ProfilRepository profilRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final TypeDocumentRepository typeDocumentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(MedDjammApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MedDjammApplication.class, args);

        //    createDirectoryIfItDoesntExist();
    }

    /*
    private static void createDirectoryIfItDoesntExist() {
        Path path = Paths.get(System.getProperty("src/main/resources/static/webapp/document") + "/multi_ged/dmi");

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ie) {
                LOG.error(String.format("Problem creating directory %s", path));
            }
        }
    }*/

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

        Utilisateur utilisateur = Utilisateur.builder().email("root@test").prenom("Tairou").nom("Diallo").matricule(genererMatricule()).motdepasse(passwordEncoder.encode("root")).profil(profileAdmin).actif(true).build();
        utilisateurRepository.save(utilisateur);

        var admin = RegisterRequest.builder().firstname("Admin").prenom("Admin").email("admin@mail.com").password("password").profilCode("ADMIN").build();
        System.out.println("Admin token: " + authenticationService.register(admin).getAccessToken());

        var manager = RegisterRequest.builder().firstname("User").prenom("User").email("manager@mail.com").password("password").profilCode("USER").build();
        System.out.println("Manager token: " + authenticationService.register(manager).getAccessToken());



        /*
        var manager = RegisterRequest.builder().firstname("User012").prenom("User012").email("mana12ger@mail.com").password("password").profilCode("ADMIN").build();
        System.out.println("Manager token: " + authenticationService.register(manager).getAccessToken());

        var manager = RegisterRequest.builder().firstname("Salif").prenom("Salif").email("salifger@mail.com").password("password").profilCode("ADMIN").build();
        System.out.println("Manager token: " + authenticationService.register(manager).getAccessToken()); */


        /*
        TypeDocument typeDocument1 = new TypeDocument(1L, "TYPE_PHOTO_PAT", "Photo du patient", 1);
        TypeDocument typeDocument2 = new TypeDocument(2L, "TYPE_PHOTO_PROF", "Photo profile agent", 1);
        TypeDocument typeDocument3 = new TypeDocument(3L, "TYPE_EXAM_CONS_BIO", "Consultation biologie", 1);
        TypeDocument typeDocument4 = new TypeDocument(4L, "TYPE_EXAM_CONS_IMMUNO", "Consultation immunologie", 1);
        TypeDocument typeDocument5 = new TypeDocument(5L, "TYPE_EXAM_CONS_IMG", "Consultation imagerie", 1);
        TypeDocument typeDocument6 = new TypeDocument(6L, "TYPE_EXAM_CONS_ANA", "Consultation anatologie", 1);
        TypeDocument typeDocument7 = new TypeDocument(7L, "TYPE_EXAM_BIO_COMP", "Examen biologie", 1);
        TypeDocument typeDocument8 = new TypeDocument(8L, "TYPE_EXAM_IMMUNO_COMP", "Examen immunologie", 1);
        TypeDocument typeDocument9 = new TypeDocument(9L, "TYPE_EXAM_IMG_COMP", "Examen imagerie", 1);
        TypeDocument typeDocument10 = new TypeDocument(10L, "TYPE_EXAM_ANA_COMP", "Examen anatologie", 1);
        TypeDocument typeDocument11 = new TypeDocument(11L, "TYPE_PROTOCOLE_MEDIC", "Protocole médical", 1);

        List<TypeDocument> typeDocumentList = new ArrayList<>();
        typeDocumentList.add(typeDocument1);
        typeDocumentList.add(typeDocument2);
        typeDocumentList.add(typeDocument3);
        typeDocumentList.add(typeDocument4);
        typeDocumentList.add(typeDocument5);
        typeDocumentList.add(typeDocument6);
        typeDocumentList.add(typeDocument7);
        typeDocumentList.add(typeDocument8);
        typeDocumentList.add(typeDocument9);
        typeDocumentList.add(typeDocument10);
        typeDocumentList.add(typeDocument11);

        typeDocumentRepository.saveAllAndFlush(typeDocumentList);

        */


    }
}
