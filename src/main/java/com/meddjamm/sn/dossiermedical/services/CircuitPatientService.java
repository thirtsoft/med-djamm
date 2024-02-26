package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;

import java.util.List;

public interface CircuitPatientService {

    CircuitPatient saveCircuitPatient(CircuitPatient circuitPatient);

    CircuitPatient updateCircuitPatient(Long id, CircuitPatient circuitPatient) throws Exception;

    CircuitPatient findById(Long id);

    CircuitPatient findByNumero(int numero);

    CircuitPatient findCircuitPatientsByPatient(String code);

    List<CircuitPatient> findAllCircuitPatients();

    void deleteCircuitPatient(Long id);
}
