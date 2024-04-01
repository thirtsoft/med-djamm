package com.meddjamm.sn.config.service;

import com.meddjamm.sn.config.entity.Profil;

import java.util.List;

public interface ProfilService {

    Profil saveProfil(Profil profil);

    Profil updateProfil(Long id, Profil profil) throws Exception;

    List<Profil> findAllActive();

    Profil findProfilById(Long id);

    Profil findByCode(String code);

    Profil findByLibelle(String libelle);

    Profil findByProfilCode(String code);

    Profil findByCodeFromAction(String code);

    void deleteProfil(Long id);
}
