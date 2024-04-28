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

INSERT INTO ged_type_document (id, code, libelle, actif)
VALUES (1, 'TYPE_PHOTO_PAT', 'Photo du patient', 1),
       (2, 'TYPE_PHOTO_PROF', 'Photo profile pour un agent',1),
       (3, 'TYPE_EXAM_CONS_BIO', 'Consultation examen biologie',1),
       (4, 'TYPE_EXAM_CONS_IMMUNO', 'Consultation examen immunologie',1),
       (5, 'TYPE_EXAM_CONS_IMG', 'Consultation examen imagerie',1),
       (6, 'TYPE_EXAM_CONS_ANA', 'Consultation examen',1),
       (7, 'TYPE_EXAM_BIO_COMP', 'Examen complementaire biologie',1),
       (8, 'TYPE_EXAM_IMMUNO_COMP', 'Examen complementaire immunologie',1),
       (9, 'TYPE_EXAM_IMG_COMP', 'Examen complementaire imagerie',1),
       (10, 'TYPE_EXAM_ANA_COMP', 'Examen complementaire anatologie',1);
       (11, 'TYPE_PROTOCOLE_MEDIC', 'Protocole médical',1);




