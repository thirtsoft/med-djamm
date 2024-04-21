package com.meddjamm.sn.rh.piecejointe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meddjamm.sn.rh.assembler.TypeDocumentAssembler;
import com.meddjamm.sn.rh.entity.TypeDocument;
import com.meddjamm.sn.rh.services.TypeDocumentService;
import com.meddjamm.sn.utils.ConstantDeployment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PiecesJointesService {

    private final FileUtilsService fileUtilsService;

    private final PiecesJointesRepository piecesJointesRepository;

    private final ObjectMapper objectMapper;

    private final PiecesJointesAssembler piecesJointesAssembler;

    private final TypeDocumentAssembler typeDocumentAssembler;

    private final TypeDocumentService typeDocumentService;


    private String url = ConstantDeployment.ged_dmi;

    public PiecesJointesService(FileUtilsService fileUtilsService,
                                PiecesJointesRepository piecesJointesRepository,
                                ObjectMapper objectMapper,
                                PiecesJointesAssembler piecesJointesAssembler,
                                TypeDocumentAssembler typeDocumentAssembler,
                                TypeDocumentService typeDocumentService) {
        this.fileUtilsService = fileUtilsService;
        this.piecesJointesRepository = piecesJointesRepository;
        this.objectMapper = objectMapper;
        this.piecesJointesAssembler = piecesJointesAssembler;
        this.typeDocumentAssembler = typeDocumentAssembler;
        this.typeDocumentService = typeDocumentService;
    }

    public Long savePiecesJointes(MultipartFile fichier, String dtoStr) throws IOException {
        PiecesJointesDs dto = objectMapper.readValue(dtoStr, PiecesJointesDs.class);
        int nbr = piecesJointesRepository.findTotalDocuments() + 1;
        PiecesJointes model1 = null;
        if (dto.getIdDocument() == null) {
            model1 = piecesJointesRepository.findPiecesJointesByObjectIdAndType(dto.getObjectId(), dto.getTypeDocumentId());
        } else {
            model1 = piecesJointesRepository.findPiecesJointesByObjectIdAndTypeAndIdDocument(dto.getObjectId(), dto.getTypeDocumentId(), dto.getIdDocument());
        }
        PiecesJointes model2 = new PiecesJointes(dto);
        String extension = fileUtilsService.getFileExtension(fichier.getOriginalFilename());
        model2.setNomFichier(fichier.getOriginalFilename());
        TypeDocument typ = typeDocumentService.findTypeDocumentById(model2.getTypeDocumentId());
        model2.setNomTechnique(typ.getCode() + "_" + StringUtils.leftPad("" + nbr, 9, '0') + "." + extension);
        Path basePath = getRepertoire(dto.getObjectId(), dto.getDossier());
        Path fileStorageLocation = basePath.toAbsolutePath().normalize();
        try {
            if (model1 != null) {
                model2.setId(model1.getId());
                Path toDelete = basePath.resolve(model1.getNomTechnique());
                Files.deleteIfExists(toDelete);
            }
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Impossible de créer le dossier de sauvegarde des fichiers à uploader.", ex);
        }
        try {
            Path targetLocation = fileStorageLocation.resolve(model2.getNomTechnique());
            Files.write(targetLocation, fichier.getBytes());
            model2 = piecesJointesRepository.save(model2);
            return model2.getId();
        } catch (Exception ex) {
            throw new FileStorageException("Impossible d'enregistrer le fichier " + model2.getNomFichier() + ".", ex);
        }
    }

    public Long savePiecesJointes(byte[] fichier, PiecesJointesDs dto) throws IOException {
        int nbr = piecesJointesRepository.findTotalDocuments() + 1;
        PiecesJointes model1 = null;
        if (dto.getIdDocument() == null) {
            model1 = piecesJointesRepository.findPiecesJointesByObjectIdAndType(dto.getObjectId(), dto.getTypeDocumentId());
        } else {
            model1 = piecesJointesRepository.findPiecesJointesByObjectIdAndTypeAndIdDocument(dto.getObjectId(), dto.getTypeDocumentId(), dto.getIdDocument());
        }
        PiecesJointes model2 = new PiecesJointes(dto);

        String extension = "pdf";
        TypeDocument typ = typeDocumentService.findTypeDocumentById(model2.getTypeDocumentId());
        model2.setNomTechnique(typ.getCode() + StringUtils.leftPad("" + nbr, 9, '0') + "." + extension);
        model2.setNomFichier(model2.getNomTechnique());
        Path basePath = getRepertoire(dto.getObjectId(), dto.getDossier());
        Path fileStorageLocation = basePath.toAbsolutePath().normalize();
        try {
            if (model1 != null) {
                model2.setId(model1.getId());
                Path toDelete = basePath.resolve(model1.getNomTechnique());
                Files.deleteIfExists(toDelete);
            }
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Impossible de créer le dossier de sauvegarde des fichiers à uploader.", ex);
        }
        try {
            Path targetLocation = fileStorageLocation.resolve(model2.getNomTechnique());
            Files.write(targetLocation, fichier);
            model2 = piecesJointesRepository.save(model2);
            return model2.getId();
        } catch (Exception ex) {
            throw new FileStorageException("Impossible d'enregistrer le fichier " + model2.getNomFichier() + ".", ex);
        }
    }

    private Path getRepertoire(Long objectId, String dossier) {
        return Paths.get(url).toAbsolutePath().normalize().resolve("dossier_" + objectId + "/"
                + dossier);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Map getContentPiecesJointesDTO(Long id) throws PieceJointeException {
        PiecesJointes pieceJointe = piecesJointesRepository.findPiecesJointesById(id);
        try {
            Path path = Paths.get(url).toAbsolutePath().normalize().resolve("dossier_" + pieceJointe.getObjectId() + "/"
                    + pieceJointe.getDossier() + "/" + pieceJointe.getNomTechnique());
            Map body = new HashMap<>();
            body.put("content", Files.readAllBytes(path));

            // Retrieve extension
            body.put("extension", fileUtilsService.getFileExtension(pieceJointe.getNomTechnique()));

            return body;
        } catch (IOException e) {
            //throw new PieceJointeException("Impossible de lire le content de la piéce jointe : "+e.getMessage());
        }
        return null;
    }

    public List<String> getUrlPieceJointesByDocumentIdAndTypeDocument(Long id, Long typeDocumentId) {
        List<PiecesJointes> piecesJointes = piecesJointesRepository.findByActifAndObjectIdAndTypeDocumentId(1, id, typeDocumentId);
        List<String> list = new ArrayList<>();
        for (PiecesJointes pj : piecesJointes) {
            Path path = Paths.get(url).toAbsolutePath().normalize()
                    .resolve("dossier_" + pj.getObjectId() + "/" + pj.getDossier() + "/" + pj.getNomTechnique());
            try {
                byte[] fileContent = Files.readAllBytes(path);
                String encodedString = Base64.getEncoder().encodeToString(fileContent);
                String ext = fileUtilsService.getFileExtension(pj.getNomTechnique());
                list.add("data:image/" + ext.toLowerCase() + ";base64," + encodedString);
            } catch (IOException e) {
            }
        }
        return list;
    }

    public PiecesJointesDs getPieceJointesByDocumentIdAndTypeDocument(Long id, Long typeDocumentId) {
        PiecesJointes piecesJointes = piecesJointesRepository.findByObjectIdAndTypeDocumentIdAndActif(id, typeDocumentId, 1).orElse(null);
        return piecesJointesAssembler.createPieceJointeDs(piecesJointes);
    }

    private PiecesJointesDs getPiecesJointesDTO(PiecesJointes piecesJointes) throws PieceJointeException {
        if (piecesJointes == null) {
            return null;
        }
        PiecesJointesDs pieceJointeDTO = piecesJointesAssembler.createPieceJointeDs(piecesJointes);
        return pieceJointeDTO;
    }
}
