create sequence MAT_SEG_GEN
 increment by 1
 start with 5000000000
 maxvalue 9999999999999999
 minvalue 1
 cache 150
 cycle;

 create sequence GEN_SEG_GEN
  increment by 1
  start with 5000000
  maxvalue 9999999999999999
  minvalue 1
  cache 150
  cycle;
--
--INSERT INTO ACTION (ID, CODE, LIBELLE, CREATION_DATE, LAST_MODIFIED_DATE, CREATED_BY_USER, LAST_MODIFIED_BY)
--VALUES
--(GEN_SEG_GEN.nextval, "aujout", "aujout", CURRENT_DATE, CURRENT_DATE, "root", "root"),
--(GEN_SEG_GEN.nextval, "modifier", "modifier", CURRENT_DATE, CURRENT_DATE, "root", "root"),
--(GEN_SEG_GEN.nextval, "supprimer", "supprimer", CURRENT_DATE, CURRENT_DATE, "root", "root"),
--(GEN_SEG_GEN.nextval, "consulter", "consulter", CURRENT_DATE, CURRENT_DATE, "root", "root"),
--(GEN_SEG_GEN.nextval, "envoyer", "envoyer", CURRENT_DATE, CURRENT_DATE, "root", "root"),
--(GEN_SEG_GEN.nextval, "annuler", "annuler", CURRENT_DATE, CURRENT_DATE, "root", "root");
--
--
--CREATE TABLE PROFIL
--(
--    ID NUMERIC(19),
--    VERSION NUMERIC(19),
--    CODE VARCHAR(50) NOT NULL,
--    LIBELLE VARCHAR(50) NOT NULL,
--    ACTIF NUMERIC(1),
--    CREATION_DATE TIMESTAMP,
--    LAST_MODIFIED_DATE TIMESTAMP,
--    CREATED_BY_USER VARCHAR(50),
--    LAST_MODIFIED_BY VARCHAR(50)
--);

    create table profil (
        id bigint not null,
        version bigint,
        code varchar(255),
        libelle varchar(255),
        actif integer,
        creation_date timestamp(6) not null,
        last_modified_date timestamp(6),
        created_by_user varchar(255) not null,
        last_modified_by varchar(255),
        primary key (id)
    );

INSERT INTO PROFIL (ID,VERSION, CODE, LIBELLE, ACTIF, CREATION_DATE, LAST_MODIFIED_DATE, CREATED_BY_USER, LAST_MODIFIED_BY)
VALUES
    (1, 0, 'ADMIN', 'Administrateur', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'root', 'root');