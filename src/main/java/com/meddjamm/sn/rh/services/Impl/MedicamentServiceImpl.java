package com.meddjamm.sn.rh.services.Impl;

import com.meddjamm.sn.rh.entity.Medicament;
import com.meddjamm.sn.rh.repository.MedicamentRepository;
import com.meddjamm.sn.rh.services.MedicamentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MedicamentServiceImpl implements MedicamentService {

    private final MedicamentRepository medicamentRepository;

    public MedicamentServiceImpl(MedicamentRepository medicamentRepository) {
        this.medicamentRepository = medicamentRepository;
    }

    @Override
    public Medicament saveMedicament(Medicament medicament) {
        if (findByCode(medicament.getCode()) != null)
            log.info("This medicament is exist");
        medicament.setActif(true);
        medicament.setCreateDate(new Date());
        return medicamentRepository.save(medicament);
    }

    @Override
    public Medicament updateMedicament(Long id, Medicament medicament) throws Exception {
        if (!medicamentRepository.existsById(id)) {
            log.info("This Medicament that id is " + id + "not found");
        }
        Medicament medicamentResult = medicamentRepository.findMedicamentById(id);
        if (medicamentResult == null) {
            throw new Exception("This Medicament is not found");
        }
        medicamentResult.setCode(medicament.getCode());
        medicamentResult.setLibelle(medicament.getLibelle());
        return medicamentRepository.save(medicamentResult);
    }

    @Override
    public Medicament findById(Long id) {
        return medicamentRepository.findById(id).orElseThrow(() -> new RuntimeException("This medicament that id " + id + "is not found"));
    }

    @Override
    public Medicament findByCode(String code) {
        return medicamentRepository.findMedicamentByCode(code);
    }

    @Override
    public List<Medicament> findAllMedicaments() {
        return medicamentRepository.findAll();
    }

    @Override
    public void deleteMedicament(Long id) {
        Medicament medicament = findById(id);
        medicament.setActif(false);
        medicamentRepository.save(medicament);
    }
}
