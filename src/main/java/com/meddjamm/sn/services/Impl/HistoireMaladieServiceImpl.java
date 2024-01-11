package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.HistoireMaladie;
import com.meddjamm.sn.repository.HistoireMaladieRepository;
import com.meddjamm.sn.services.HistoireMaladieService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoireMaladieServiceImpl implements HistoireMaladieService {

    private final HistoireMaladieRepository histoireMaladieRepository;

    public HistoireMaladieServiceImpl(HistoireMaladieRepository histoireMaladieRepository) {
        this.histoireMaladieRepository = histoireMaladieRepository;
    }

    @Override
    public void saveHistoireMaladie(HistoireMaladie histoireMaladie) {
        histoireMaladie.setActif(true);
        histoireMaladie.setCreatedDate(new Date());
        histoireMaladieRepository.save(histoireMaladie);
    }

    @Override
    public void updateHistoireMaladie(Long id, HistoireMaladie histoireMaladie) throws Exception {
        if (!histoireMaladieRepository.existsById(id)) {
            throw new Exception("Histoire maladie id not found");
        }
        HistoireMaladie histoireMaladieResult = histoireMaladieRepository.findHistoireMaladieById(id);
        if (histoireMaladieResult == null) {
            throw new Exception("Histoire maladie not found");
        }
        histoireMaladieResult.setAge(histoireMaladie.getAge());
        histoireMaladieResult.setDateDiagnostic(histoireMaladie.getDateDiagnostic());
        histoireMaladieResult.setCirconstance(histoireMaladie.getCirconstance());
        histoireMaladieResult.setAntecedentPrecedents(histoireMaladie.getAntecedentPrecedents());
        histoireMaladieResult.setAntecedentActuels(histoireMaladie.getAntecedentActuels());
        histoireMaladieResult.setInfoComplementaire(histoireMaladie.getInfoComplementaire());
        histoireMaladieRepository.save(histoireMaladieResult);
    }

    @Override
    public HistoireMaladie findById(Long id) {
        return histoireMaladieRepository.findHistoireMaladieById(id);
    }

    @Override
    public List<HistoireMaladie> findHistoireMaladiesByPatient(String indexPatient) {
        return histoireMaladieRepository.findHistoireMaladiesByPatient(indexPatient);
    }

    @Override
    public void deleteHistoireMaladie(Long id) {
        HistoireMaladie histoireMaladie = findById(id);
        histoireMaladie.setActif(false);
        histoireMaladieRepository.save(histoireMaladie);
    }
}
