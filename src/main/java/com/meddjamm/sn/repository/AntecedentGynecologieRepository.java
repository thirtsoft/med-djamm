package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.AntecedentGynecologie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntecedentGynecologieRepository extends JpaRepository<AntecedentGynecologie, Long> {

    @Query("SELECT DISTINCT p from AntecedentGynecologie p where p.id=:id and p.actif=1")
    AntecedentGynecologie findAntecedentGynecologieById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from AntecedentGynecologie p where p.indexPatient=:indexPatient and p.actif=1 order by p.id desc")
    List<AntecedentGynecologie> findAllAntecedentGynecologiesByPatient(@Param("indexPatient") String indexPatient);

}
