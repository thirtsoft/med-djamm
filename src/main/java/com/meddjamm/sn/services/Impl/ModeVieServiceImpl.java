package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.ModeVie;
import com.meddjamm.sn.repository.ModeVieRepository;
import com.meddjamm.sn.services.ModeVieService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ModeVieServiceImpl implements ModeVieService {

    private final ModeVieRepository modeVieRepository;

    public ModeVieServiceImpl(ModeVieRepository modeVieRepository) {
        this.modeVieRepository = modeVieRepository;
    }

    @Override
    public void saveModeVie(ModeVie modeVie) {
        modeVie.setActif(true);
        modeVie.setCreatedDate(new Date());
        modeVieRepository.save(modeVie);
    }

    @Override
    public void updateModeVie(Long id, ModeVie modeVie) throws Exception {
        if(!modeVieRepository.existsById(id)) {
            new Exception("This ModeVie is not exist");
        }
        ModeVie modeVieResult = modeVieRepository.findModeVieById(id);
        if (modeVieResult == null) {
            new Exception("This ModeVie is not exist");
        }
        modeVieResult.setLibelle(modeVie.getLibelle());
        modeVieRepository.save(modeVieResult);
    }

    @Override
    public ModeVie findById(Long id) {
        return modeVieRepository.findModeVieById(id);
    }

    @Override
    public List<ModeVie> findAllModeVieByPatient(String indexPatient) {
        return modeVieRepository.findAllModeViesByPatient(indexPatient);
    }

    @Override
    public void deleteModeVie(Long id) {
        ModeVie modeVie = findById(id);
        modeVie.setActif(false);
        modeVieRepository.save(modeVie);
    }
}
