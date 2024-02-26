package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.Ordonnance;
import com.meddjamm.sn.repository.OrdonnanceRepository;
import com.meddjamm.sn.services.OrdonnanceService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdonnanceServiceImpl implements OrdonnanceService {

    private final OrdonnanceRepository ordonnanceRepository;

    public OrdonnanceServiceImpl(OrdonnanceRepository ordonnanceRepository) {
        this.ordonnanceRepository = ordonnanceRepository;
    }

    @Override
    public Ordonnance saveOrdonnance(Ordonnance ordonnance) {
        ordonnance.setActif(true);
        ordonnance.setCreatedDate(new Date());
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
        return ordonnanceRepository.findById(id).orElseThrow(()-> new RuntimeException(
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
}