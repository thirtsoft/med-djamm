package com.meddjamm.sn;

import com.meddjamm.sn.repository.ClassificationRepository;
import com.meddjamm.sn.rh.repository.MaladieRepository;
import com.meddjamm.sn.dossiermedical.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedDjammApplication implements CommandLineRunner {

    private final MaladieRepository maladieRepository;
    private final ClassificationRepository classificationRepository;
    private final PatientRepository patientRepository;

    public MedDjammApplication(MaladieRepository maladieRepository, ClassificationRepository classificationRepository, PatientRepository patientRepository) {
        this.maladieRepository = maladieRepository;
        this.classificationRepository = classificationRepository;
        this.patientRepository = patientRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MedDjammApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /*
        Classification cl1 = classificationRepository.save(new Classification(1L, "PCR", new Date(), 1));
        Classification cl2 = classificationRepository.save(new Classification(2L, "POR", new Date(), 1));
        Classification cl3 = classificationRepository.save(new Classification(3L, "ABC", new Date(), 1));
        Classification cl4 = classificationRepository.save(new Classification(4L, "ABBA", new Date(), 1));
        Classification cl5 = classificationRepository.save(new Classification(5L, "FANA", new Date(), 1));

        Maladie m1 = maladieRepository.save(new Maladie(1L, "Paludisme", new Date(), 1));
        Maladie m2 = maladieRepository.save(new Maladie(2L, "Diarrhe", new Date(), 1));
        Maladie m3 = maladieRepository.save(new Maladie(3L, "Mot de tete", new Date(), 1));
        Maladie m4 = maladieRepository.save(new Maladie(4L, "Bas ventre", new Date(), 1));

        Patient p1 = patientRepository.save(new Patient("1L", "Tairou", "Diallo", "Mr", "Mariste", "779440310", 1));
        Patient p2 = patientRepository.save(new Patient("2L", "Saliou woury", "Diallo", "Mr", "Mariste", "779440310", 1));
        Patient p3 = patientRepository.save(new Patient("3L", "Binta", "Diallo", "Me", "Mariste", "779440310", 1));
        Patient p4 = patientRepository.save(new Patient("4L", "Aicha", "Diallo", "Me", "Mariste", "779440310", 1));
        Patient p5 = patientRepository.save(new Patient("5L", "Lama", "Diallo", "Mr", "Mariste", "779440310", 1));

        */
    }
}
