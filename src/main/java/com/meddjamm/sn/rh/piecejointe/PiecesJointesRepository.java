package com.meddjamm.sn.rh.piecejointe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PiecesJointesRepository extends JpaRepository<PiecesJointes, Long> {

    @Query("SELECT DISTINCT doc FROM PiecesJointes doc WHERE doc.id=:id")
    PiecesJointes findPiecesJointesById(@Param("id") Long id);

    @Query("SELECT count(DISTINCT doc) FROM PiecesJointes doc")
    int findTotalDocuments();

    @Query("SELECT DISTINCT doc FROM PiecesJointes doc WHERE doc.typeDocumentId=:type AND doc.objectId=:id and doc.actif=1")
    PiecesJointes findPiecesJointesByObjectIdAndType(@Param("id") Long id, @Param("type") Long type);

    Optional<PiecesJointes> findByObjectIdAndTypeDocumentIdAndActif(Long id, Long typeDocumentId, int i);

    Optional<PiecesJointes> findByObjectIdAndTypeDocumentId(Long objectId, Long idDocument);

    @Query("SELECT DISTINCT doc FROM PiecesJointes doc WHERE doc.typeDocumentId=:type AND doc.objectId=:id and doc.idDocument = :idDocument and doc.actif=1")
    PiecesJointes findPiecesJointesByObjectIdAndTypeAndIdDocument(@Param("id") Long id, @Param("type") Long type, @Param("idDocument") Long idDocument);

    List<PiecesJointes> findByActifAndObjectIdAndTypeDocumentId(int actif, Long patientId, Long typeDocumentId);

    List<PiecesJointes> findByActifAndObjectId(int actif, Long demandeId);
}
