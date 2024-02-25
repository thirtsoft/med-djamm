package com.meddjamm.sn.rh.services.Impl;

import com.meddjamm.sn.rh.entity.Specialite;
import com.meddjamm.sn.rh.repository.SpecialiteRepository;
import com.meddjamm.sn.rh.services.SpecialiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SpecialiteServiceImpl implements SpecialiteService {

    private final SpecialiteRepository specialiteRepository;

    public SpecialiteServiceImpl(SpecialiteRepository specialiteRepository) {
        this.specialiteRepository = specialiteRepository;
    }

    @Override
    public void addSpeciality(Specialite specialite) {
        if (specialiteRepository.findSpecialiteByDesignation(specialite.getDesignation()) != null) {
            log.info("This speciality is existing");
        }
        specialite.setActif(true);
        specialite.setDateCreated(new Date());
        specialiteRepository.save(specialite);
    }

    @Override
    public void updateSpeciality(Long id, Specialite specialite) {
        if (!specialiteRepository.existsById(id)) {
            log.info("This speciality is not existing");
        }
        specialite.setId(id);
        specialiteRepository.save(specialite);
    }

    @Override
    public Specialite findById(Long id) {
        return specialiteRepository.findSpecialiteById(id);
    }

    @Override
    public List<Specialite> findAllSpecialities() {
        return specialiteRepository.findAllSpecialites();
    }

    @Override
    public void deleteSpeciality(Long id) {
        Specialite specialite = findById(id);
        specialite.setActif(false);
        specialiteRepository.save(specialite);
    }
}
