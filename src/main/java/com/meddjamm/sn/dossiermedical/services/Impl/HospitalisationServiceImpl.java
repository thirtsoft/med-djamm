package com.meddjamm.sn.dossiermedical.services.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meddjamm.sn.dossiermedical.entity.ExamenComplementaire;
import com.meddjamm.sn.dossiermedical.entity.Hospitalisation;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.HospitalisationRepository;
import com.meddjamm.sn.dossiermedical.services.HospitalisationService;
import com.meddjamm.sn.rh.piecejointe.PiecesJointes;
import com.meddjamm.sn.rh.piecejointe.PiecesJointesService;
import com.meddjamm.sn.utils.ConstantSigps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
@Slf4j
public class HospitalisationServiceImpl implements HospitalisationService {

    private final HospitalisationRepository hospitalisationRepository;

    private final PiecesJointesService piecesJointesService;

    private final CircuitPatientRepository circuitPatientRepository;

    public HospitalisationServiceImpl(HospitalisationRepository hospitalisationRepository,
                                      PiecesJointesService piecesJointesService,
                                      CircuitPatientRepository circuitPatientRepository) {
        this.hospitalisationRepository = hospitalisationRepository;
        this.piecesJointesService = piecesJointesService;
        this.circuitPatientRepository = circuitPatientRepository;
    }

    @Override
    public Hospitalisation saveHospitalisation(Hospitalisation hospitalisation) {
        hospitalisation.setActif(true);
        hospitalisation.setCreatedDate(new Date());
        if (hospitalisation.getNumeroHospitalisation() == 0) {
            hospitalisation.setNumeroHospitalisation(createNumeroHospitalisation());
        }
//        CircuitPatient circuitPatient = circuitPatientRepository.findCircuitPatientById(hospitalisation.getCircuitPatientId());
//        hospitalisation.setCircuitPatientId(circuitPatient.getId());
//        hospitalisation.setCircuitPatient(circuitPatient);
        return hospitalisationRepository.save(hospitalisation);
    }


    @Override
    public Hospitalisation updateHospitalisation(Long id, Hospitalisation hospitalisation) {
        if (!hospitalisationRepository.existsById(id)) {
            log.info("Hospitalisation that id is " + id + "is not found");
        }
        Hospitalisation hospitalisationResult = hospitalisationRepository.findHospitalisationById(id);
        if (hospitalisationResult == null) {
            log.info("Hospitalisation that id is " + id + "is not found");
        }
        assert hospitalisationResult != null;
        hospitalisationResult.setResume(hospitalisation.getResume());
        hospitalisationResult.setDiscussion(hospitalisation.getDiscussion());
        hospitalisationResult.setExamenComplementaire(hospitalisation.getExamenComplementaire());
        hospitalisationResult.setObservationClinique(hospitalisation.getObservationClinique());
        hospitalisationResult.setSynthese(hospitalisation.getSynthese());
        hospitalisationResult.setTraitementMedical(hospitalisation.getTraitementMedical());
        hospitalisationResult.setMatricule(hospitalisation.getMatricule());
        return hospitalisationRepository.save(hospitalisation);
    }

    @Override
    public Hospitalisation findById(Long id) {
        return hospitalisationRepository.findHospitalisationById(id);
    }

    @Override
    public List<Hospitalisation> findAllHospitalisations() {
        return hospitalisationRepository.findAllHospitalisations();
    }

    @Override
    public List<Hospitalisation> findAllByPatient(String code) {
        return hospitalisationRepository.findAllByPatient(code);
    }

    @Override
    public void deleteHospitalisation(Long id) {
        Hospitalisation hospitalisation = findById(id);
        hospitalisation.setActif(false);
        hospitalisationRepository.save(hospitalisation);
    }

    @Override
    public boolean addExamBiologicToHospitalisation(Long biologicExamId, MultipartFile biologic) throws Exception {
        Hospitalisation hospitalisation = findById(biologicExamId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (hospitalisation != null) {
                ExamenComplementaire examenComplementaire = hospitalisation.getExamenComplementaire();
                if (biologic != null) {
                    PiecesJointes piecesJointesDTO = new PiecesJointes();
                    piecesJointesDTO.setObjectId(examenComplementaire.getId());
                    piecesJointesDTO.setDossier("pieces_jointes");
                    piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_EXAM_BIO_COMP);
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
    public boolean addExamImmunologicToHospitalisation(Long immunologicExamId, MultipartFile immunologic) throws Exception {
        Hospitalisation hospitalisation = findById(immunologicExamId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (hospitalisation != null) {
                ExamenComplementaire examenComplementaire = hospitalisation.getExamenComplementaire();
                if (immunologicExamId != null) {
                    PiecesJointes piecesJointesDTO = new PiecesJointes();
                    piecesJointesDTO.setObjectId(examenComplementaire.getId());
                    piecesJointesDTO.setDossier("pieces_jointes");
                    piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_EXAM_IMMUNO_COMP);
                    piecesJointesDTO.setNomFichier(immunologic.getName());
                    piecesJointesService.savePiecesJointes(immunologic, objectMapper.writeValueAsString(piecesJointesDTO));
                    return true;
                } else {
                    throw new Exception("Hospitalisation n'existe pas.");
                }
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean addExamImagerToHospitalisation(Long imagerExamId, MultipartFile imager) throws Exception {
        Hospitalisation hospitalisation = findById(imagerExamId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (hospitalisation != null) {
                ExamenComplementaire examenComplementaire = hospitalisation.getExamenComplementaire();
                if (imager != null) {
                    PiecesJointes piecesJointesDTO = new PiecesJointes();
                    piecesJointesDTO.setObjectId(examenComplementaire.getId());
                    piecesJointesDTO.setDossier("pieces_jointes");
                    piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_EXAM_IMG_COMP);
                    piecesJointesDTO.setNomFichier(imager.getName());
                    piecesJointesService.savePiecesJointes(imager, objectMapper.writeValueAsString(piecesJointesDTO));
                    return true;
                } else {
                    throw new Exception("La hospitalisation n'existe pas.");
                }
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean addExamHematologicToHospitalisation(Long hematologicExamId, MultipartFile hematologic) throws Exception {
        Hospitalisation hospitalisation = findById(hematologicExamId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (hospitalisation != null) {
                ExamenComplementaire examenComplementaire = hospitalisation.getExamenComplementaire();
                if (hematologic != null) {
                    PiecesJointes piecesJointesDTO = new PiecesJointes();
                    piecesJointesDTO.setObjectId(examenComplementaire.getId());
                    piecesJointesDTO.setDossier("pieces_jointes");
                    piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_EXAM_ANA_COMP);
                    piecesJointesDTO.setNomFichier(hematologic.getName());
                    piecesJointesService.savePiecesJointes(hematologic, objectMapper.writeValueAsString(piecesJointesDTO));
                    return true;
                } else {
                    throw new Exception("L'hospitalisation n'existe pas.");
                }
            }

            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private synchronized int createNumeroHospitalisation() {
        int nbr = 0;
        try {
            nbr = hospitalisationRepository.maxNumeroHospitalisation();

        } catch (Exception e) {
        }
        return (nbr + 1);
    }
}
