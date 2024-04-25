package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.entity.Ordonnance;
import com.meddjamm.sn.dossiermedical.entity.OrdonnanceItem;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.OrdonnanceItemRepository;
import com.meddjamm.sn.dossiermedical.repository.OrdonnanceRepository;
import com.meddjamm.sn.dossiermedical.services.OrdonnanceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OrdonnanceServiceImpl implements OrdonnanceService {

    private final OrdonnanceRepository ordonnanceRepository;

    private final CircuitPatientRepository circuitPatientRepository;

    private final OrdonnanceItemRepository ordonnanceItemRepository;

    public OrdonnanceServiceImpl(OrdonnanceRepository ordonnanceRepository,
                                 CircuitPatientRepository circuitPatientRepository,
                                 OrdonnanceItemRepository ordonnanceItemRepository) {
        this.ordonnanceRepository = ordonnanceRepository;
        this.circuitPatientRepository = circuitPatientRepository;
        this.ordonnanceItemRepository = ordonnanceItemRepository;
    }


    @Override
    public Ordonnance saveOrdonnance(Ordonnance ordonnance) {
        ordonnance.setActif(true);
        ordonnance.setCreatedDate(new Date());
        CircuitPatient circuitPatient = circuitPatientRepository.findCircuitPatientById(ordonnance.getCircuitPatientId());
        ordonnance.setCircuitPatientId(circuitPatient.getId());
        ordonnance.setCircuitPatient(circuitPatient);
        return ordonnanceRepository.save(ordonnance);
    }

    @Override
    public Ordonnance updateOrdonnance(Long id, Ordonnance ordonnance) throws Exception {
        if (!ordonnanceRepository.existsById(id)) {
            throw new Exception("This Ordonnance that id is " + id + "not found");
        }
        Ordonnance ordonnanceResult = ordonnanceRepository.findOrdonnanceById(id);
        if (ordonnanceResult == null) {
            throw new Exception("This Ordonnance is not found");
        }
        Set<OrdonnanceItem> ordonnanceItemSet = ordonnance.getOrdonnanceItems();
        for (OrdonnanceItem ordonnanceItem : ordonnanceItemSet) {
            if (ordonnanceItem != null) {
                ordonnanceItemRepository.save(ordonnanceItem);
            }
        }
        ordonnanceResult.setOrdonnanceItems(ordonnanceItemSet);
        return ordonnanceRepository.save(ordonnanceResult);
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

    @Override
    public OrdonnanceItem findOrdonnanceItemById(Long id) {
        return ordonnanceItemRepository.findOrdonnanceItemById(id);
    }

    @Override
    public List<Ordonnance> findOrdonnancesByCircuitId(Long circuitId) {
        return ordonnanceRepository.findOrdonnanceByCircuitId(circuitId);
    }
}