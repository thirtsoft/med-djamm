package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.AntecedentChirurgie;
import com.meddjamm.sn.entity.DiagnosticAssocie;
import com.meddjamm.sn.repository.AntecedentChirurgieRepository;
import com.meddjamm.sn.services.AntecedentChirurgieService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AntecedentChirurgieServiceImpl implements AntecedentChirurgieService {

    private final AntecedentChirurgieRepository antecedentChirurgieRepository;

    public AntecedentChirurgieServiceImpl(AntecedentChirurgieRepository antecedentChirurgieRepository) {
        this.antecedentChirurgieRepository = antecedentChirurgieRepository;
    }

    @Override
    public void saveAntecedentChirurgie(AntecedentChirurgie antecedentChirurgie) {
        antecedentChirurgie.setActif(true);
        antecedentChirurgie.setCreatedDate(new Date());
        antecedentChirurgieRepository.save(antecedentChirurgie);
    }

    @Override
    public void updateAntecedentChirurgie(Long id, AntecedentChirurgie antecedentChirurgie) throws Exception {
        if (!antecedentChirurgieRepository.existsById(id)) {
            throw new Exception("This AntecedentChirurgie is not found");
        }
        AntecedentChirurgie antecedentChirurgieResult = antecedentChirurgieRepository.findAntecedentChirurgieById(id);
        if (antecedentChirurgieResult == null) {
            throw new Exception("This AntecedentChirurgie is not found");
        }
        antecedentChirurgieResult.setLibelle(antecedentChirurgie.getLibelle());
        antecedentChirurgieRepository.save(antecedentChirurgieResult);
    }

    @Override
    public AntecedentChirurgie findById(Long id) {
        return antecedentChirurgieRepository.findAntecedentChirurgieById(id);
    }

    @Override
    public List<AntecedentChirurgie> findAllAntecedentChirurgieByPatient(String indexPatient) {
        return antecedentChirurgieRepository.findAllAntecedentChirurgiesByPatient(indexPatient);
    }

    @Override
    public void deleteAntecedentChirurgie(Long id) {
        AntecedentChirurgie antecedentChirurgie = findById(id);
        antecedentChirurgie.setActif(false);
        antecedentChirurgieRepository.save(antecedentChirurgie);
    }
}
