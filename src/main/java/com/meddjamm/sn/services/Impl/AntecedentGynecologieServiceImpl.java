package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.AntecedentGynecologie;
import com.meddjamm.sn.repository.AntecedentGynecologieRepository;
import com.meddjamm.sn.services.AntecedentGynecologieService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class AntecedentGynecologieServiceImpl implements AntecedentGynecologieService {

    private final AntecedentGynecologieRepository antecedentGynecologieRepository;

    public AntecedentGynecologieServiceImpl(AntecedentGynecologieRepository antecedentGynecologieRepository) {
        this.antecedentGynecologieRepository = antecedentGynecologieRepository;
    }

    @Override
    public void saveAntecedentGynecologie(AntecedentGynecologie antecedentGynecologie) {
        antecedentGynecologie.setActif(true);
        antecedentGynecologie.setCreatedDate(new Date());
        antecedentGynecologieRepository.save(antecedentGynecologie);
    }

    @Override
    public void updateAntecedentGynecologie(Long id, AntecedentGynecologie antecedentGynecologie) throws Exception {
        if (!antecedentGynecologieRepository.existsById(id)) {
            throw new Exception("This AntecedentGynecologie is not found");
        }
        AntecedentGynecologie antecedentGynecologieResult = antecedentGynecologieRepository.findAntecedentGynecologieById(id);
        if (antecedentGynecologieResult == null) {
            throw new Exception("This AntecedentGynecologie is not found");
        }
     //   antecedentGynecologieResult.setLibelle(antecedentGynecologie.getLibelle());
        antecedentGynecologieResult.setGynecologiquesAntecedent(new HashSet<>(antecedentGynecologie.getGynecologiquesAntecedent()));
        antecedentGynecologieRepository.save(antecedentGynecologieResult);
    }

    @Override
    public AntecedentGynecologie findById(Long id) {
        return antecedentGynecologieRepository.findAntecedentGynecologieById(id);
    }

    @Override
    public List<AntecedentGynecologie> findAllAntecedentGynecologieByPatient(String indexPatient) {
        return null;
    }

    @Override
    public void deleteAntecedentGynecologie(Long id) {
        AntecedentGynecologie antecedentGynecologie = findById(id);
        antecedentGynecologie.setActif(false);
        antecedentGynecologieRepository.save(antecedentGynecologie);
    }
}
