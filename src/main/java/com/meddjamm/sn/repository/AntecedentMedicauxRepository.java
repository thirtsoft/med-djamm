package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.AntecedentMedicaux;
import com.meddjamm.sn.entity.DiagnosticAssocie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntecedentMedicauxRepository extends JpaRepository<AntecedentMedicaux, Long> {

    @Query("SELECT DISTINCT p from AntecedentMedicaux p where p.id=:id and p.actif=1")
    AntecedentMedicaux findAntecedentMedicauxById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from AntecedentMedicaux p where p.indexPatient=:indexPatient and p.actif=1 order by p.id desc")
    List<AntecedentMedicaux> findAllAntecedentMedicauxByPatient(@Param("indexPatient") String indexPatient);
}
