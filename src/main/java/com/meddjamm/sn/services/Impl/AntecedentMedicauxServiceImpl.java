package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.AntecedentMedicaux;
import com.meddjamm.sn.repository.AntecedentMedicauxRepository;
import com.meddjamm.sn.services.AntecedentMedicauxService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AntecedentMedicauxServiceImpl implements AntecedentMedicauxService {

    private final AntecedentMedicauxRepository antecedentMedicauxRepository;

    public AntecedentMedicauxServiceImpl(AntecedentMedicauxRepository antecedentMedicauxRepository) {
        this.antecedentMedicauxRepository = antecedentMedicauxRepository;
    }

    @Override
    public void saveAntecedentMedicaux(AntecedentMedicaux antecedentMedicaux) {
        antecedentMedicaux.setActif(true);
        antecedentMedicaux.setCreateDate(new Date());
        antecedentMedicauxRepository.save(antecedentMedicaux);
    }

    @Override
    public void updateAntecedentMedicaux(Long id, AntecedentMedicaux antecedentMedicaux) throws Exception {

    }

    @Override
    public AntecedentMedicaux findById(Long id) {
        return antecedentMedicauxRepository.findAntecedentMedicauxById(id);
    }

    @Override
    public List<AntecedentMedicaux> findAllAntecedentMedicauxByPatient(String indexPatient) {
        return null;
    }

    @Override
    public void deleteAntecedentMedicaux(Long id) {
        AntecedentMedicaux antecedentMedicaux = findById(id);
        antecedentMedicaux.setActif(false);
        antecedentMedicauxRepository.save(antecedentMedicaux);
    }
}
