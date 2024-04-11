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

    @Query("SELECT DISTINCT p from CircuitPatient p where p.code=:code and p.actif=1 order by p.id desc")
    CircuitPatient findCircuitPatientByPatient(@Param("code") String code);

    @Query("SELECT DISTINCT p from CircuitPatient p where p.actif=1 order by p.id desc")
    List<CircuitPatient> findAllCircuitPatients();

    @Query("SELECT DISTINCT max(act.numeroCircuit) FROM CircuitPatient act")
    int maxNumeroCircuitPatient();

    @Query("SELECT DISTINCT p from CircuitPatient p where p.code=:code and p.actif=1 order by p.id desc")
    List<CircuitPatient> findAllCircuitPatientsByPatient(@Param("code") String code);

}
