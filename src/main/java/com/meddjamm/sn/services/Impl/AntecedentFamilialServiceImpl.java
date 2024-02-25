package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.AntecedentFamilial;
import com.meddjamm.sn.repository.AntecedentFamilialRepository;
import com.meddjamm.sn.services.AntecedentFamilialService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class AntecedentFamilialServiceImpl implements AntecedentFamilialService {

    private final AntecedentFamilialRepository antecedentFamilialRepository;

    public AntecedentFamilialServiceImpl(AntecedentFamilialRepository antecedentFamilialRepository) {
        this.antecedentFamilialRepository = antecedentFamilialRepository;
    }

    @Override
    public void saveAntecedentFamilial(AntecedentFamilial antecedentFamilial) {
        antecedentFamilial.setActif(true);
        antecedentFamilial.setCreatedDate(new Date());
        antecedentFamilialRepository.save(antecedentFamilial);

    }

    @Override
    public void updateAntecedentFamilial(Long id, AntecedentFamilial antecedentFamilial) throws Exception {
        if (!antecedentFamilialRepository.existsById(id)) {
            throw new Exception("Antecedent familial not found");
        }
        AntecedentFamilial antecedentFamilialResult = antecedentFamilialRepository.findAntecedentFamilialById(id);
        if (antecedentFamilialResult == null) {
            throw new Exception("Antecedent not found");
        }
        /*
        antecedentFamilialResult.setAntecedentAscendant(antecedentFamilial.getAntecedentAscendant());
        antecedentFamilialResult.setAntecedentCollateral(antecedentFamilial.getAntecedentCollateral());
        antecedentFamilialResult.setAntecedentDescendant(antecedentFamilial.getAntecedentDescendant());*/

        antecedentFamilialResult.setFamilialsAntecedentAscendant(new HashSet<>(antecedentFamilial.getFamilialsAntecedentAscendant()));
        antecedentFamilialResult.setFamilialsAntecedentCollateral(new HashSet<>(antecedentFamilial.getFamilialsAntecedentCollateral()));
        antecedentFamilialResult.setFamilialsAntecedentDescendant(new HashSet<>(antecedentFamilial.getFamilialsAntecedentDescendant()));
        antecedentFamilialRepository.save(antecedentFamilialResult);
    }

    @Override
    public AntecedentFamilial findById(Long id) {
        return antecedentFamilialRepository.findAntecedentFamilialById(id);
    }

    @Override
    public List<AntecedentFamilial> findAllAntecedentFamilialByPatient(String indexPatient) {
        return null;
    }

    @Override
    public void deleteAntecedentFamilial(Long id) {
        AntecedentFamilial antecedentFamilial = findById(id);
        antecedentFamilial.setActif(false);
        antecedentFamilialRepository.save(antecedentFamilial);
    }
}
