package com.meddjamm.sn.dossiermedical.services.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.entity.ConsultationMedical;
import com.meddjamm.sn.dossiermedical.entity.ExamenBiologique;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.ConsultationMedicalRepository;
import com.meddjamm.sn.dossiermedical.services.ConsultationMedicalService;
import com.meddjamm.sn.dossiermedical.services.PatientService;
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

    private final PatientService patientService;

    public ConsultationMedicalServiceImpl(ConsultationMedicalRepository consultationMedicalRepository,
                                          CircuitPatientRepository circuitPatientRepository,
                                          PiecesJointesService piecesJointesService,
                                          PatientService patientService) {
        this.consultationMedicalRepository = consultationMedicalRepository;
        this.circuitPatientRepository = circuitPatientRepository;
        this.piecesJointesService = piecesJointesService;
        this.patientService = patientService;
    }

    @Override
    public Long saveConsultationMedical(ConsultationMedical consultationMedical) {
        consultationMedical.setActif(true);
        consultationMedical.setCreatedDate(new Date());
        CircuitPatient circuitPatient = circuitPatientRepository.findCircuitPatientById(consultationMedical.getCircuitPatientId());
        consultationMedical.setCircuitPatientId(circuitPatient.getId());
        consultationMedical.setCircuitPatient(circuitPatient);
        ConsultationMedical saveconsultationMedical = consultationMedicalRepository.save(consultationMedical);
        Patient patient = patientService.findByCode(saveconsultationMedical.getCircuitPatient().getCode());
        try {
            if (patient != null) {
                patient.setNombre_passage(patient.getNombre_passage() + 1);
                patientService.savePatient(patient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveconsultationMedical.getId();
    }

    @Override
    public Long updateConsultationMedical(Long id, ConsultationMedical consultationMedical) throws Exception {
        if (!consultationMedicalRepository.existsById(id)) {
            log.info("ConsultationMedical that this id " + id + "not found");
        }
        ConsultationMedical consultationMedicalResult = consultationMedicalRepository.findConsultationMedicalById(id);
        if (consultationMedicalResult == null) {
            throw new Exception("This ConsultationMedical is not found");
        }
        consultationMedicalResult.setConsultation(consultationMedical.getConsultation());
        consultationMedicalResult.setExamenBiologique(consultationMedical.getExamenBiologique());
        ConsultationMedical updatedconsultationMedical = consultationMedicalRepository.save(consultationMedical);
        return updatedconsultationMedical.getId();
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
        objectMapper.registerModule(new JavaTimeModule());
        try {
            if (examenBiologique != null) {
                if (biologic != null) {
                    PiecesJointes piecesJointesDTO = piecesJointesService.findByObjectIdAndIdDocument
                            (examenBiologique.getId(), ConstantSigps.TYPE_EXAM_CONS_BIO).orElse(new PiecesJointes());
                    piecesJointesDTO.setObjectId(examenBiologique.getId());
                    piecesJointesDTO.setDossier("examen_biologie");
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
        objectMapper.registerModule(new JavaTimeModule());
        try {
            if (examenBiologique != null) {
                if (immunologic != null) {
                    PiecesJointes piecesJointesDTO = piecesJointesService.findByObjectIdAndIdDocument
                            (examenBiologique.getId(), ConstantSigps.TYPE_EXAM_CONS_IMMUNO).orElse(new PiecesJointes());
                    piecesJointesDTO.setObjectId(examenBiologique.getId());
                    piecesJointesDTO.setDossier("examen_biologie");
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
        objectMapper.registerModule(new JavaTimeModule());
        try {
            if (examenBiologique != null) {
                if (imager != null) {
                    PiecesJointes piecesJointesDTO = piecesJointesService.findByObjectIdAndIdDocument
                            (examenBiologique.getId(), ConstantSigps.TYPE_EXAM_CONS_IMG).orElse(new PiecesJointes());
                    piecesJointesDTO.setObjectId(examenBiologique.getId());
                    piecesJointesDTO.setDossier("examen_biologie");
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
        objectMapper.registerModule(new JavaTimeModule());
        try {
            if (examenBiologique != null) {
                if (hematologic != null) {
                    PiecesJointes piecesJointesDTO = piecesJointesService.findByObjectIdAndIdDocument
                            (examenBiologique.getId(), ConstantSigps.TYPE_EXAM_CONS_ANA).orElse(new PiecesJointes());
                    piecesJointesDTO.setObjectId(examenBiologique.getId());
                    piecesJointesDTO.setDossier("examen_biologie");
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

    @Override
    public List<ConsultationMedical> findConsultationMedicalByCircuitId(Long circuitId) {
        return consultationMedicalRepository.findConsultationMedicalByCircuitId(circuitId);
    }
}
