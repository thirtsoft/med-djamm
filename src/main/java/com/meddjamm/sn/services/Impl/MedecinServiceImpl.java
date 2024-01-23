package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.Medecin;
import com.meddjamm.sn.repository.MedecinRepository;
import com.meddjamm.sn.services.MedecinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MedecinServiceImpl implements MedecinService {

    private final MedecinRepository medecinRepository;

    public MedecinServiceImpl(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        medecin.setActif(true);
        medecin.setDateRecrutement(new Date());
        return medecinRepository.save(medecin);
    }

    @Override
    public Medecin updateMedecin(Long id, Medecin medecin) throws Exception {
        return null;
    }

    @Override
    public Medecin findById(Long id) {
        return medecinRepository.findById(id).orElse(null);
    }

    @Override
    public List<Medecin> findAllMedecins() {
        return medecinRepository.findAll();
    }

    @Override
    public void deleteMedecin(Long id) {
        Medecin medecin = findById(id);
        medecin.setActif(false);
        medecinRepository.deleteById(id);
    }
}
