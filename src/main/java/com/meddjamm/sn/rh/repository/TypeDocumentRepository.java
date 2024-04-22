package com.meddjamm.sn.rh.repository;

import com.meddjamm.sn.rh.entity.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {

    @Query("Select DISTINCT act from TypeDocument act where act.actif=1 ORDER BY act.libelle")
    List<TypeDocument> findAll();

    @Query("Select DISTINCT act from TypeDocument act where act.id=:id and act.actif=1 ORDER BY act.libelle")
    TypeDocument findTypeDocumentById(@Param("id") Long id);

    @Query("Select DISTINCT act from TypeDocument act where act.actif=1 and act.code=:code")
    TypeDocument findTypeDocumentByCode(@Param("code") String code);
}
