package com.meddjamm.sn.config.service.impl;

import com.meddjamm.sn.config.entity.Profil;
import com.meddjamm.sn.config.repository.ProfilRepository;
import com.meddjamm.sn.config.service.ProfilService;
import com.meddjamm.sn.exception.ProfilNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProfilServiceImpl implements ProfilService {

    private final ProfilRepository profilRepository;

    public ProfilServiceImpl(ProfilRepository profilRepository) {
        this.profilRepository = profilRepository;
    }

    @Override
    public Profil saveProfil(Profil profil) {
        if (findByCode(profil.getCode()) != null) {
            log.info("This profil exist");
        }
        profil.setActif(true);
        return profilRepository.save(profil);
    }

    @Override
    public Profil updateProfil(Long id, Profil profil) throws Exception {
        if (!profilRepository.existsById(id)) {
            log.info("This profil that id is " + id + "not found");
        }
        Profil profilResult = profilRepository.findProfilById(id);
        if (profilResult == null) {
            throw new Exception("This Profil is not found");
        }
        profilResult.setCode(profil.getCode());
        profilResult.setLibelle(profil.getLibelle());
        profilResult.setAction(profil.getAction());
        return profilRepository.save(profilResult);
    }

    @Override
    public List<Profil> findAllActive() {
        return profilRepository.findAllActive();
    }

    @Override
    public Profil findProfilById(Long id) {
        return profilRepository.findProfilById(id);
    }

    @Override
    public Profil findByCode(String code) {
        return profilRepository.findByCode(code);
    }

    @Override
    public Profil findByLibelle(String libelle) {
        return profilRepository.findByLibelle(libelle);
    }

    @Override
    public Profil findByProfilCode(String code) {
        return profilRepository.findByProfilCode(code)
                .orElseThrow(() -> new ProfilNotFoundException("Profil inconnu"));
    }

    @Override
    public Profil findByCodeFromAction(String code) {
        return profilRepository.findByCodeFromAction(code);
    }

    @Override
    public void deleteProfil(Long id) {
        Profil profil = findProfilById(id);
        profil.setActif(false);
        profilRepository.save(profil);
    }
}
