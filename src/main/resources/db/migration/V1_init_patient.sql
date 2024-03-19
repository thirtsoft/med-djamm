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
       (1, 33, True, 0, NULL, '2024-03-08 00:32:15.949', '2024-03-07 00:00:00', 2, 2, 'Mariste', 'Mme',
        'PAT_02', NULL, NULL, NULL, NULL, 'Maria', '765432156', NULL, NULL, NULL, NULL, 'Maria',
        'Assistant', NULL, NULL, NULL, 'Femme', 'Mariée'),
       (1, 33, True, 0, NULL, '2024-03-08 21:47:28.398', '2024-03-08 00:00:00', 3, 3, 'Mariste', 'M.',
        'PATIENT_03', NULL, NULL, NULL, NULL, 'Abass', '778104567', NULL, NULL, NULL, NULL, 'Mamoud',
        'Homme Affaire', NULL, NULL, NULL, 'Homme', 'Mariée'),
       (1, 0, False, 0, NULL, '2024-03-08 22:01:51.85', '2024-03-08 00:00:00', 4, 4, 'Malika', 'Homme',
        'PATIENT_04', NULL, NULL, NULL, NULL, 'Mane', '782134567', NULL, NULL, NULL, NULL, 'Salif', NULL,
        NULL, NULL, NULL, 'Homme', NULL);

INSERT INTO medecin(actif, date_recrutement, id, speciality, civilite, email, matricule, nom, prenom, sexe, telephone)
Values (1, '2024-03-08 00:21:34.27', 1, NULL, 'M.', 'laye@gmail.com', '2345', 'Samb', 'Absoulaye', 'Homme',
        '781234567'),
       (1, '2024-03-08 00:27:11.341', 2, NULL, 'M.', 'seydina@gmail.com', '543', 'Diouf', 'Seydina', 'Homme',
        '78321456');

INSERT INTO medicament(id, code, libelle, create_date, actif)
Values (1,'AA','Artésunate/Amodiaquine ','2024-03-08 00:21:34.27', 1),
        (2,'AL','Artéméther/Luméfantrine ','2024-03-08 00:21:34.27', 1),
        (3,'QB','Quinine Base  ', '2024-03-08 00:21:34.27', 1),
        (4,'Q','Quinine', '2024-03-08 00:21:34.27', 1),
        (5,'A','Amikacine', '2024-03-08 00:21:34.27', 1),
        (6,'AAC','Amoxicilline+Acide Clavulanique  ', '2024-03-08 00:21:34.27', 1),
        (7,'B','Benzylpénicilline ', '2024-03-08 00:21:34.27', 1),
        (8,'Bb','Benzathine-benzylpénicilline  ', '2024-03-08 00:21:34.27', 1),
        (9,'Ce','Céfixime ', '2024-03-08 00:21:34.27', 1),
        (10,'Ch','Chloramphénicol ', '2024-03-08 00:21:34.27', 1),
        (11,'D','Doxycycline', '2024-03-08 00:21:34.27', 1),
        (12,'Er','Erythromycine  ', '2024-03-08 00:21:34.27', 1),
        (13,'Gen','Gentamycine', '2024-03-08 00:21:34.27', 1),
        (14,'Oxa','Oxacilline' ,'2024-03-08 00:21:34.27', 1),
        (15,'Ph','Phénoxyméthylpénicilline' ,'2024-03-08 00:21:34.27', 1),
        (16,'SuT','Sulfaméthoxazole+Trméthoprime ' ,'2024-03-08 00:21:34.27', 1),
        (17,'Et','Ethambutol', '2024-03-08 00:21:34.27', 1),
        (18,'Para','Paracétamol', '2024-03-08 00:21:34.27', 1),
        (19,'Fu','Furosémide', '2024-03-08 00:21:34.27', 1),
        (20,'Dop','Dopamine ', '2024-03-08 00:21:34.27', 1);






