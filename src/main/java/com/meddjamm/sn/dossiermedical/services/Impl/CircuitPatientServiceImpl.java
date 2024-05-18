package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.PatientRepository;
import com.meddjamm.sn.dossiermedical.services.CircuitPatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CircuitPatientServiceImpl implements CircuitPatientService {

    private final CircuitPatientRepository circuitPatientRepository;

    private final PatientRepository patientRepository;

    public CircuitPatientServiceImpl(CircuitPatientRepository circuitPatientRepository,
                                     PatientRepository patientRepository) {
        this.circuitPatientRepository = circuitPatientRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public CircuitPatient saveCircuitPatient(CircuitPatient circuitPatient) {
        circuitPatient.setActif(true);
        circuitPatient.setCreateDate(new Date());
        if (circuitPatient.getNumeroCircuit() == 0) {
            circuitPatient.setNumeroCircuit(createNumeroCircuit());
        }
        Patient patient = patientRepository.findPatientByCode(circuitPatient.getCode());
        if (Objects.equals(patient.getSexe(), "Homme"))
            circuitPatient.setTypePatient(1);
        circuitPatient.setTypePatient(0);
        circuitPatientRepository.save(circuitPatient);
        patient.setIsCircuitGenerated(1);
        patientRepository.save(patient);
        return circuitPatient;
    }

    @Override
    public CircuitPatient updateCircuitPatient(Long id, CircuitPatient circuitPatient) throws Exception {
        if (!circuitPatientRepository.existsById(id))
            log.info("Circuit that id is " + id + "not found");
        circuitPatient.setId(id);
        return circuitPatientRepository.save(circuitPatient);
    }

    @Override
    public CircuitPatient findById(Long id) {
        return circuitPatientRepository.findCircuitPatientById(id);
    }

    @Override
    public CircuitPatient findCircuitPatientsByPatient(String code) {
        return circuitPatientRepository.findCircuitPatientByPatient(code);
    }

    @Override
    public CircuitPatient findByNumero(int numero) {
        return circuitPatientRepository.findCircuitPatientByNumero(numero);
    }

    @Override
    public List<CircuitPatient> findAllCircuitPatients() {
        return circuitPatientRepository.findAllCircuitPatients();
    }

    @Override
    public List<CircuitPatient> findAllCircuitPatientsByPatient(String code) {
        return circuitPatientRepository.findAllCircuitPatientsByPatient(code);
    }

    @Override
    public void deleteCircuitPatient(Long id) {
        CircuitPatient circuitPatient = findById(id);
        circuitPatient.setActif(false);
        circuitPatientRepository.save(circuitPatient);
    }

    public synchronized int createNumeroCircuit() {
        int nbr = 0;
        try {
            nbr = circuitPatientRepository.maxNumeroCircuitPatient();

        } catch (Exception e) {
        }
        return (nbr + 1);
    }
}
