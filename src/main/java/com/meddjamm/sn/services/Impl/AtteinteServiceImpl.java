package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.Atteinte;
import com.meddjamm.sn.repository.AtteinteRepository;
import com.meddjamm.sn.services.AtteinteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AtteinteServiceImpl implements AtteinteService {

    private final AtteinteRepository atteinteRepository;

    public AtteinteServiceImpl(AtteinteRepository atteinteRepository) {
        this.atteinteRepository = atteinteRepository;
    }

    @Override
    public void saveAtteinte(Atteinte atteinte) {
        if (atteinteRepository.findCritereMaladieByLibelle(atteinte.getLibelle()) != null) {
            log.info("This Critere existe");
        }
        atteinte.setActif(true);
        atteinte.setCreateDate(new Date());
        atteinteRepository.save(atteinte);
    }

    @Override
    public void updateAtteinte(Long id, Atteinte atteinte) throws Exception {

    }

    @Override
    public Atteinte findById(Long id) {
        return atteinteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Atteinte> findAllAtteintes() {
        return atteinteRepository.findAll();
    }

    @Override
    public void deleteAtteinte(Long id) {
        Atteinte atteinte = findById(id);
        atteinte.setActif(false);
        atteinteRepository.save(atteinte);
    }
}
