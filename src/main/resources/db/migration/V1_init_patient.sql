INSERT INTO personne_confiance(id, email, nom, prenom, telephone)
VALUES (1, 'khadji@gmail.com', 'Diallo', 'Khadidiatou', '774321567'),
       (2, 'fanta@gmail.com', 'Dabo', 'Fanta', '789056789'),
       (3, 'souleymane@gmail.com', 'Diop', 'Souleymane', '77123456'),
       (4, 'mamy@gmail.com', 'Diouf', 'Mamy', '783115678');

INSERT INTO patient(actif, age, est_accompagne, is_circuit_generated, created_by, date_admission, date_naissance, id,
                    personne_confiance_id, address, civilite, code, consanguinite, ethnie, nationalite,
                    niveau_social_economique, nom, numero_telephone, origine, origine_mere, origine_pere,
                    photo, prenom, profession, prototype, race, regime_alimentaire, sexe,
                    situation_matrimonial)
Values (1, 28, True, 0, NULL, '2024-03-08 00:29:48.519', '2024-03-08 00:00:00', 1, 1, 'Hann-mariste 2',
        'M.', 'PAT_01', NULL, NULL, NULL, NULL, 'Diallo', '789440310', NULL, NULL, NULL, NULL, 'Tairou',
        'Pro', NULL, NULL, NULL, 'Homme', 'Mariée'),
       (1, 33, True, 1, NULL, '2024-03-08 00:32:15.949', '2024-03-07 00:00:00', 2, 2, 'Mariste', 'Mme',
        'PAT_02', NULL, NULL, NULL, NULL, 'Maria', '765432156', NULL, NULL, NULL, NULL, 'Maria',
        'Assistant', NULL, NULL, NULL, 'Femme', 'Mariée'),
       (1, 33, True, 1, NULL, '2024-03-08 21:47:28.398', '2024-03-08 00:00:00', 3, 3, 'Mariste', 'M.',
        'PATIENT_03', NULL, NULL, NULL, NULL, 'Abass', '778104567', NULL, NULL, NULL, NULL, 'Mamoud',
        'Homme Affaire', NULL, NULL, NULL, 'Homme', 'Mariée'),
       (1, 0, False, 1, NULL, '2024-03-08 22:01:51.85', '2024-03-08 00:00:00', 4, 4, 'Malika', 'Homme',
        'PATIENT_04', NULL, NULL, NULL, NULL, 'Mane', '782134567', NULL, NULL, NULL, NULL, 'Salif', NULL,
        NULL, NULL, NULL, 'Homme', NULL);

INSERT INTO medecin(actif, date_recrutement, id, speciality, civilite, email, matricule, nom, prenom, sexe, telephone)
Values (1, '2024-03-08 00:21:34.27', 1, NULL, 'M.', 'laye@gmail.com', '2345', 'Samb', 'Absoulaye', 'Homme',
        '781234567'),
       (1, '2024-03-08 00:27:11.341', 2, NULL, 'M.', 'seydina@gmail.com', '543', 'Diouf', 'Seydina', 'Homme',
        '78321456');

