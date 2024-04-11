package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.entity.Ordonnance;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.OrdonnanceRepository;
import com.meddjamm.sn.dossiermedical.services.OrdonnanceService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdonnanceServiceImpl implements OrdonnanceService {

    private final OrdonnanceRepository ordonnanceRepository;

    private final CircuitPatientRepository circuitPatientRepository;

    public OrdonnanceServiceImpl(OrdonnanceRepository ordonnanceRepository,
                                 CircuitPatientRepository circuitPatientRepository) {
        this.ordonnanceRepository = ordonnanceRepository;
        this.circuitPatientRepository = circuitPatientRepository;
    }


    @Override
    public Ordonnance saveOrdonnance(Ordonnance ordonnance) {
        ordonnance.setActif(true);
        ordonnance.setCreatedDate(new Date());
        CircuitPatient circuitPatient = circuitPatientRepository.findCircuitPatientById(ordonnance.getCircuitPatientId());
        circuitPatient.setType("Ordonnance");
        circuitPatientRepository.save(circuitPatient);
        ordonnance.setCircuitPatientId(circuitPatient.getId());
        ordonnance.setCircuitPatient(circuitPatient);
        return ordonnanceRepository.save(ordonnance);
    }

    @Override
    public Ordonnance updateOrdonnance(Long id, Ordonnance ordonnance) throws Exception {
        if (!ordonnanceRepository.existsById(id)) {
            new Exception("This ordonnance that id is " + id + "not found");
        }
        ordonnance.setId(id);
        return ordonnanceRepository.save(ordonnance);
    }

    @Override
    public Ordonnance findById(Long id) {
        return ordonnanceRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "This ordonnance that id is " + id + "not found"
        ));
    }

    @Override
    public List<Ordonnance> findAllOrdonnances() {
        return ordonnanceRepository.findAll();
    }

    @Override
    public void deleteOrdonnance(Long id) {
        Ordonnance ordonnance = findById(id);
        ordonnance.setActif(false);
        ordonnanceRepository.save(ordonnance);
    }

    @Override
    public List<Ordonnance> findOrdonnancesByPatientId(String code) {
        return ordonnanceRepository.findOrdonnanceByPatientId(code);
    }
}