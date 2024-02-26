package com.meddjamm.sn.rh.services;

import com.meddjamm.sn.rh.entity.Profil;

import java.util.List;
import java.util.Optional;

public interface ProfilService {

    Profil saveProfil(Profil profil);

    Profil updateProfil(Long id, Profil profil) throws Exception;

    List<Profil> findAllActive();

    Profil findProfilById(Long id);

    Profil findByCode(String code);

    Profil findByLibelle(String libelle);

    Optional<Profil> findByProfilCode(String code);

    Profil findByCodeFromAction(String code);

    void deleteProfil(Long id);
}
