package com.meddjamm.sn.rh.services.Impl;

import com.meddjamm.sn.rh.entity.CodagePatient;
import com.meddjamm.sn.rh.repository.CodagePatientRepository;
import com.meddjamm.sn.rh.services.CodagePatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodagePatientServiceImpl implements CodagePatientService {

    private final CodagePatientRepository codagePatientRepository;

    @Override
    public Long saveCodagePatient(CodagePatient codagePatient) {
        codagePatient.setActif(true);
        CodagePatient savedcodagePatient = codagePatientRepository.save(codagePatient);
        return savedcodagePatient.getId();
    }

    @Override
    public Long updateCodagePatient(Long id, CodagePatient codagePatient) throws Exception {
        if (!codagePatientRepository.existsById(id)) {
            throw new Exception("Codage that id is " + id + "not found");
        }
        CodagePatient codagePatientResult = codagePatientRepository.findCodagePatientById(id);
        if (codagePatientResult == null) {
            throw new Exception("Codage not found");
        }
        codagePatientResult.setPatientId(codagePatient.getPatientId());
        codagePatientResult.setEpistemologies(new HashSet<>(codagePatient.getEpistemologies()));
        codagePatientResult.setCliniques(new HashSet<>(codagePatient.getCliniques()));
        codagePatientResult.setExamenComplementaires(new HashSet<>(codagePatient.getExamenComplementaires()));
        codagePatientResult.setTraitements(new HashSet<>(codagePatient.getTraitements()));
        codagePatientResult.setSyntheses(new HashSet<>(codagePatient.getSyntheses()));
        CodagePatient updatedcodagePatient = codagePatientRepository.save(codagePatientResult);
        return updatedcodagePatient.getId();
    }

    @Override
    public CodagePatient findById(Long id) {
        return codagePatientRepository.findCodagePatientById(id);
    }

    @Override
    public List<CodagePatient> findAllCodagePatients() {
        return codagePatientRepository.findAllActiveCodagePatient();
    }

    @Override
    public void deleteCodagePatient(Long id) {
        CodagePatient codagePatient = findById(id);
        codagePatient.setActif(false);
        codagePatientRepository.save(codagePatient);
    }
}
