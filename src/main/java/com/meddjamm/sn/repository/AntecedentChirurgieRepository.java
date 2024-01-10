package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.AntecedentChirurgie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntecedentChirurgieRepository extends JpaRepository<AntecedentChirurgie, Long> {

    @Query("SELECT DISTINCT p from AntecedentChirurgie p where p.id=:id and p.actif=1")
    AntecedentChirurgie findAntecedentChirurgieById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from AntecedentChirurgie p where p.indexPatient=:indexPatient and p.actif=1 order by p.id desc")
    List<AntecedentChirurgie> findAllAntecedentChirurgiesByPatient(@Param("indexPatient") String indexPatient);

}
