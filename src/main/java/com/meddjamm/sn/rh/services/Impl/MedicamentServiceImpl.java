package com.meddjamm.sn.rh.services.Impl;

import com.meddjamm.sn.rh.entity.Medicament;
import com.meddjamm.sn.rh.repository.MedicamentRepository;
import com.meddjamm.sn.rh.services.MedicamentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MedicamentServiceImpl implements MedicamentService {

    private final MedicamentRepository medicamentRepository;

    public MedicamentServiceImpl(MedicamentRepository medicamentRepository) {
        this.medicamentRepository = medicamentRepository;
    }

    @Override
    public Long saveMedicament(Medicament medicament) throws Exception {
        String code = medicament.getCode();
        Optional<Medicament> byCode = medicamentRepository.findByCode(code);
        if (medicament.getId() == null && byCode.isPresent()
                || (medicament.getId() != null && byCode.isPresent() && !byCode.get().getId().equals(medicament.getId()))) {
            throw new Exception(String.format("Le code %s est déjà associé à un autre médicament .", code));
        }
        medicament.setActif(true);
        medicament.setCreateDate(new Date());
        Medicament savedMedicament = medicamentRepository.save(medicament);
        return savedMedicament.getId();
    }

    @Override
    public Long updateMedicament(Long id, Medicament medicament) throws Exception {
        if (!medicamentRepository.existsById(id)) {
            log.info("This Medicament that id is " + id + "not found");
        }
        Medicament medicamentResult = medicamentRepository.findMedicamentById(id);
        if (medicamentResult == null) {
            throw new Exception("This Medicament is not found");
        }
        medicamentResult.setCode(medicament.getCode());
        medicamentResult.setLibelle(medicament.getLibelle());
        Optional<Medicament> byCode = medicamentRepository.findByCode(medicament.getCode());
        if (medicament.getId() == null && byCode.isPresent()
                || (medicament.getId() != null && byCode.isPresent() && !byCode.get().getId().equals(medicament.getId()))) {
            throw new Exception(String.format("Le code %s est déjà associé à un autre médicament .", medicament.getCode()));
        }
        Medicament updatedMedicament = medicamentRepository.save(medicamentResult);
        return updatedMedicament.getId();
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
