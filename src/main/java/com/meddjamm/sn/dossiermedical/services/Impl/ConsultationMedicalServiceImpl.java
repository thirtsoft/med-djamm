package com.meddjamm.sn.dossiermedical.services.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.entity.ConsultationMedical;
import com.meddjamm.sn.dossiermedical.entity.ExamenBiologique;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.ConsultationMedicalRepository;
import com.meddjamm.sn.dossiermedical.services.ConsultationMedicalService;
import com.meddjamm.sn.rh.piecejointe.PiecesJointes;
import com.meddjamm.sn.rh.piecejointe.PiecesJointesService;
import com.meddjamm.sn.utils.ConstantSigps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ConsultationMedicalServiceImpl implements ConsultationMedicalService {

    private final ConsultationMedicalRepository consultationMedicalRepository;

    private final CircuitPatientRepository circuitPatientRepository;

    private final PiecesJointesService piecesJointesService;

    public ConsultationMedicalServiceImpl(ConsultationMedicalRepository consultationMedicalRepository,
                                          CircuitPatientRepository circuitPatientRepository,
                                          PiecesJointesService piecesJointesService) {
        this.consultationMedicalRepository = consultationMedicalRepository;
        this.circuitPatientRepository = circuitPatientRepository;
        this.piecesJointesService = piecesJointesService;
    }

    @Override
    public ConsultationMedical saveConsultationMedical(ConsultationMedical consultationMedical) {
        consultationMedical.setActif(true);
        consultationMedical.setCreatedDate(new Date());
        CircuitPatient circuitPatient = circuitPatientRepository.findCircuitPatientById(consultationMedical.getCircuitPatientId());
        consultationMedical.setCircuitPatientId(circuitPatient.getId());
        consultationMedical.setCircuitPatient(circuitPatient);
        return consultationMedicalRepository.save(consultationMedical);
    }

    @Override
    public ConsultationMedical updateConsultationMedical(Long id, ConsultationMedical consultationMedical) throws Exception {
        if (!consultationMedicalRepository.existsById(id)) {
            log.info("ConsultationMedical that this id " + id + "not found");
        }
        ConsultationMedical consultationMedicalResult = consultationMedicalRepository.findConsultationMedicalById(id);
        if (consultationMedicalResult == null) {
            throw new Exception("This ConsultationMedical is not found");
        }
        consultationMedicalResult.setConsultation(consultationMedical.getConsultation());
        consultationMedicalResult.setExamenBiologique(consultationMedical.getExamenBiologique());
        return consultationMedicalRepository.save(consultationMedicalResult);
    }

    @Override
    public ConsultationMedical findById(Long id) {
        return consultationMedicalRepository.findConsultationMedicalById(id);
    }

    @Override
    public List<ConsultationMedical> findAllConsultationMedicals() {
        return consultationMedicalRepository.findAllConsultationMedicals();
    }

    @Override
    public void deleteConsultationMedical(Long id) {
        ConsultationMedical consultationMedical = findById(id);
        consultationMedical.setActif(false);
        consultationMedicalRepository.save(consultationMedical);
    }


    @Override
    public List<ConsultationMedical> findConsultationMedicalByPatientId(String code) {
        return consultationMedicalRepository.findConsultationMedicalByPatientId(code);
    }

    @Override
    public boolean addConsultationBiologicToConsultation(Long biologicId, MultipartFile biologic) throws Exception {
        ConsultationMedical consultationMedical = findById(biologicId);
        ExamenBiologique examenBiologique = consultationMedical.getExamenBiologique();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (examenBiologique != null) {
                if (biologic != null) {
                    PiecesJointes piecesJointesDTO = new PiecesJointes();
                    piecesJointesDTO.setObjectId(examenBiologique.getId());
                    piecesJointesDTO.setDossier("pieces_jointes");
                    piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_EXAM_CONS_BIO);
                    piecesJointesDTO.setNomFichier(biologic.getName());
                    piecesJointesService.savePiecesJointes(biologic, objectMapper.writeValueAsString(piecesJointesDTO));
                    return true;
                } else {
                    throw new Exception("La consultation n'existe pas.");
                }
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean addConsultationImmunologicToConsultation(Long biologicId, MultipartFile immunologic) throws Exception {
        ConsultationMedical consultationMedical = findById(biologicId);
        ExamenBiologique examenBiologique = consultationMedical.getExamenBiologique();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (examenBiologique != null) {
                if (immunologic != null) {
                    PiecesJointes piecesJointesDTO = new PiecesJointes();
                    piecesJointesDTO.setObjectId(examenBiologique.getId());
                    piecesJointesDTO.setDossier("pieces_jointes");
                    piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_EXAM_CONS_IMMUNO);
                    piecesJointesDTO.setNomFichier(immunologic.getName());
                    piecesJointesService.savePiecesJointes(immunologic, objectMapper.writeValueAsString(piecesJointesDTO));
                    return true;
                } else {
                    throw new Exception("La consultation n'existe pas.");
                }
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean addConsultationImagerToConsultation(Long biologicId, MultipartFile imager) throws Exception {
        ConsultationMedical consultationMedical = findById(biologicId);
        ExamenBiologique examenBiologique = consultationMedical.getExamenBiologique();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (examenBiologique != null) {
                if (imager != null) {
                    PiecesJointes piecesJointesDTO = new PiecesJointes();
                    piecesJointesDTO.setObjectId(examenBiologique.getId());
                    piecesJointesDTO.setDossier("pieces_jointes");
                    piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_EXAM_CONS_IMG);
                    piecesJointesDTO.setNomFichier(imager.getName());
                    piecesJointesService.savePiecesJointes(imager, objectMapper.writeValueAsString(piecesJointesDTO));
                    return true;
                } else {
                    throw new Exception("La consultation n'existe pas.");
                }
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean addConsultationHematologicToConsultation(Long biologicId, MultipartFile hematologic) throws Exception {
        ConsultationMedical consultationMedical = findById(biologicId);
        ExamenBiologique examenBiologique = consultationMedical.getExamenBiologique();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (examenBiologique != null) {
                if (hematologic != null) {
                    PiecesJointes piecesJointesDTO = new PiecesJointes();
                    piecesJointesDTO.setObjectId(examenBiologique.getId());
                    piecesJointesDTO.setDossier("pieces_jointes");
                    piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_EXAM_CONS_ANA);
                    piecesJointesDTO.setNomFichier(hematologic.getName());
                    piecesJointesService.savePiecesJointes(hematologic, objectMapper.writeValueAsString(piecesJointesDTO));
                    return true;
                } else {
                    throw new Exception("La consultation n'existe pas.");
                }
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
