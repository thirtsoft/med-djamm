package com.meddjamm.sn.rh.piecejointe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/ged")
public interface PiecesJointesApi {

    @PostMapping("/upload")
    Long uploadPieceJointe(@RequestParam("file") MultipartFile file, @RequestParam("piecesjointes") String dtoStr) throws Exception;

    @GetMapping("/pieces-jointes/content/{pieceId}")
    Map<?, ?> getContentPieceJointeById(@PathVariable Long pieceId);

    @GetMapping("/object/{id}/piece-jointes/{typeDocumentId}")
    List<String> getUrlPieceJointes(@PathVariable Long id, @PathVariable Long typeDocumentId) throws Exception;

    @GetMapping("/by-programme/{programmeId}")
    List<PiecesJointesDs> getListPieceJointeProgramme(@PathVariable Long programmeId);

    @GetMapping("/if/{id}/piece-jointes/{typeDocumentId}")
    PiecesJointesDs getPieceJointes(@PathVariable Long id, @PathVariable Long typeDocumentId) throws Exception;
    
}
