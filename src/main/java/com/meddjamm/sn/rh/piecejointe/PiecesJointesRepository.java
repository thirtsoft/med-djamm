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

    @Query("SELECT DISTINCT doc FROM PiecesJointes doc WHERE doc.objectId=:id and doc.actif=1")
    List<PiecesJointes> findPiecesJointesByObjectId(@Param("id") Long id);

    @Query("SELECT DISTINCT doc FROM PiecesJointes doc WHERE doc.typeDocumentId=:type AND doc.objectId=:id and doc.actif=1")
    PiecesJointes findPiecesJointesByObjectIdAndType(@Param("id") Long id, @Param("type") Long type);

    //Optional<PiecesJointes> findByObjectIdAndActif(Long objectId, int actif);

    Optional<PiecesJointes> findByObjectIdAndTypeDocumentIdAndActif(Long id, Long typeDocumentId, int i);

    Optional<PiecesJointes> findByObjectIdAndIdDocument(Long objectId, Long idDocument);

    @Query("SELECT DISTINCT doc FROM PiecesJointes doc WHERE doc.typeDocumentId=:type AND doc.objectId=:id and doc.idDocument = :idDocument and doc.actif=1")
    PiecesJointes findPiecesJointesByObjectIdAndTypeAndIdDocument(@Param("id") Long id, @Param("type") Long type, @Param("idDocument") Long idDocument);

    List<PiecesJointes> findAllByObjectIdAndTypeDocumentIdAndActif(Long objectId, Long type, int actif);

    List<PiecesJointes> findByIdDocumentAndTypeDocumentIdAndActif(Long documentId, Long typeId, int actif);

    Optional<PiecesJointes> findByIdDocumentAndObjectIdAndActif(Long documentId, Long promoteurId, int actif);

    Optional<PiecesJointes> findByIdAndActif(Long pieceId, int actif);

    List<PiecesJointes> findByActifAndObjectIdAndTypeDocumentId(int actif, Long demandeId, Long typeDocumentId);

    List<PiecesJointes> findByObjectIdAndTypeDocumentIdIsInAndActif(Long demandeId, Long[] typeDocuments, int actif);

    List<PiecesJointes> findByActifAndObjectId(int actif, Long demandeId);
}
