package com.meddjamm.sn.rh.services.Impl;

import com.meddjamm.sn.entity.Maladie;
import com.meddjamm.sn.rh.repository.MaladieRepository;
import com.meddjamm.sn.rh.services.MaladieService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaladieServiceImpl implements MaladieService {

    private final MaladieRepository maladieRepository;

    public MaladieServiceImpl(MaladieRepository maladieRepository) {
        this.maladieRepository = maladieRepository;
    }

    @Override
    public Maladie saveMaladie(Maladie maladie) {
        maladie.setActif(true);
        maladie.setCreateDate(new Date());
        return maladieRepository.save(maladie);
    }

    @Override
    public Maladie updateMaladie(Long id, Maladie maladie) throws Exception {
        if (!maladieRepository.existsById(id)) {
            throw new Exception("This Maladie is not found");
        }
        Maladie maladieResult = maladieRepository.findMaladieById(id);
        if (maladieResult == null) {
            throw new Exception("This Maladie is not found");
        }
        maladieResult.setLibelle(maladie.getLibelle());
        return maladieRepository.save(maladieResult);
    }

    @Override
    public Maladie findById(Long id) {
        return maladieRepository.findMaladieById(id);
    }

    @Override
    public List<Maladie> findAllMaladies() {
        return maladieRepository.findAll();
    }

    @Override
    public void deleteMaladie(Long id) {
        Maladie maladie = maladieRepository.findMaladieById(id);
        maladie.setActif(false);
        maladieRepository.save(maladie);
    }
}
