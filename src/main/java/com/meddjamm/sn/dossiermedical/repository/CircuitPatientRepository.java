package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CircuitPatientRepository extends JpaRepository<CircuitPatient, Long> {

    @Query("SELECT DISTINCT p from CircuitPatient p where p.id=:id and p.actif=1")
    CircuitPatient findCircuitPatientById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from CircuitPatient p where p.numeroCircuit=:numero and p.actif=1")
    CircuitPatient findCircuitPatientByNumero(@Param("numero") int numero);

    @Query("SELECT DISTINCT p from CircuitPatient p where p.patientId=:patientId and p.actif=1 order by p.id desc")
    CircuitPatient findCircuitPatientByPatient(@Param("patientId") Long patientId);

    @Query("SELECT DISTINCT p from CircuitPatient p where p.patientId=:patientId")
    CircuitPatient findCircuitPatientByPatientId(@Param("patientId") Long patientId);

    @Query("SELECT DISTINCT p from CircuitPatient p where p.actif=1 order by p.id desc")
    List<CircuitPatient> findAllCircuitPatients();

    @Query("SELECT DISTINCT max(act.numeroCircuit) FROM CircuitPatient act")
    int maxNumeroCircuitPatient();

    @Query("SELECT DISTINCT p from CircuitPatient p where p.patientId=:patientId and p.actif=1 order by p.id desc")
    List<CircuitPatient> findAllCircuitPatientsByPatient(@Param("patientId") Long patientId);

    @Query("SELECT COUNT(c) FROM CircuitPatient c WHERE c.actif=1 ")
    long countCircuit();

}
