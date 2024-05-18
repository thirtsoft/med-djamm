package com.meddjamm.sn.rh.piecejointe;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class PiecesJointesController implements PiecesJointesApi {

    private final PiecesJointesService piecesJointesService;

    private final PiecesJointesAssembler piecesJointesAssembler;

    public PiecesJointesController(PiecesJointesService piecesJointesService,
                                   PiecesJointesAssembler piecesJointesAssembler) {
        this.piecesJointesService = piecesJointesService;
        this.piecesJointesAssembler = piecesJointesAssembler;
    }

    @Override
    public Long uploadPieceJointe(MultipartFile file, String dtoStr) throws Exception {
        return piecesJointesService.savePiecesJointes(file, dtoStr);
    }

    @Override
    public Map<?, ?> getContentPieceJointeById(Long pieceId) {
        return piecesJointesService.getContentPiecesJointesDTO(pieceId);
    }

    @Override
    public List<String> getUrlPieceJointes(Long id, Long typeDocumentId) throws Exception {
        return piecesJointesService.getUrlPieceJointesByDocumentIdAndTypeDocument(id, typeDocumentId);
    }

    @Override
    public List<PiecesJointesDs> getListPieceJointeProgramme(Long programmeId) {
        return null;
    }
    
}
