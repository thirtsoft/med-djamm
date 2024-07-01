package com.meddjamm.sn.dossiermedical.services.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.meddjamm.sn.dossiermedical.entity.ExamenComplementaire;
import com.meddjamm.sn.dossiermedical.entity.Hospitalisation;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.entity.TraitementMedical;
import com.meddjamm.sn.dossiermedical.repository.HospitalisationRepository;
import com.meddjamm.sn.dossiermedical.services.HospitalisationService;
import com.meddjamm.sn.dossiermedical.services.PatientService;
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

    private final PatientService patientService;

    public HospitalisationServiceImpl(HospitalisationRepository hospitalisationRepository,
                                      PiecesJointesService piecesJointesService,
                                      PatientService patientService) {
        this.hospitalisationRepository = hospitalisationRepository;
        this.piecesJointesService = piecesJointesService;
        this.patientService = patientService;
    }

    @Override
    public Long saveHospitalisation(Hospitalisation hospitalisation) {
        hospitalisation.setActif(true);
        hospitalisation.setCreatedDate(new Date());
        if (hospitalisation.getNumeroHospitalisation() == 0) {
            hospitalisation.setNumeroHospitalisation(createNumeroHospitalisation());
        }
        Patient patient = patientService.findByCode(hospitalisation.getCode());
        String sexe = patient.getSexe();
        if (sexe.equals(ConstantSigps.TYPE_SEXE_PATIENT)) {
            hospitalisation.setTypePatient(1);
        } else {
            hospitalisation.setTypePatient(0);
        }
        Hospitalisation hospitalisationResult = hospitalisationRepository.save(hospitalisation);
        Patient patientResult = patientService.findByCode(hospitalisation.getCode());
        try {
            if (patientResult != null) {
                patient.setNombre_passage(patient.getNombre_passage() + 1);
                patientService.savePatient(patientResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hospitalisationResult.getId();
    }


    @Override
    public Long updateHospitalisation(Long id, Hospitalisation hospitalisation) {
        if (!hospitalisationRepository.existsById(id)) {
            log.info("Hospitalisation that id is " + id + "is not found");
        }
        Hospitalisation hospitalisationResult = hospitalisationRepository.findHospitalisationById(id);
        if (hospitalisationResult == null) {
            log.info("Hospitalisation that id is " + id + "is not found");
        }
        assert hospitalisationResult != null;
        hospitalisationResult.setDiscussion(hospitalisation.getDiscussion());
        hospitalisationResult.setExamenComplementaire(hospitalisation.getExamenComplementaire());
        hospitalisationResult.setObservationClinique(hospitalisation.getObservationClinique());
        hospitalisationResult.setSynthese(hospitalisation.getSynthese());
        hospitalisationResult.setTraitementMedical(hospitalisation.getTraitementMedical());
        Hospitalisation savedHospitalisationResult = hospitalisationRepository.save(hospitalisationResult);
        return savedHospitalisationResult.getId();
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
    public boolean addExamBiologicToHospitalisation(Long hospitalisationId, MultipartFile biologic) throws Exception {
        Hospitalisation hospitalisation = findById(hospitalisationId);
        ExamenComplementaire examenComplementaire = hospitalisation.getExamenComplementaire();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            if (biologic != null) {
                PiecesJointes piecesJointesDTO = piecesJointesService.findByObjectIdAndIdDocument
                        (examenComplementaire.getId(), ConstantSigps.TYPE_EXAM_BIO_COMP).orElse(new PiecesJointes());
                piecesJointesDTO.setObjectId(examenComplementaire.getId());
                piecesJointesDTO.setDossier("examen_complementaire");
                piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_EXAM_BIO_COMP);
                piecesJointesDTO.setNomFichier(biologic.getName());
                piecesJointesService.savePiecesJointes(biologic, objectMapper.writeValueAsString(piecesJointesDTO));
                return true;
            } else {
                throw new Exception("La consultation n'existe pas.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean addExamImmunologicToHospitalisation(Long hospitalisationId, MultipartFile immunologic) throws Exception {
        Hospitalisation hospitalisation = findById(hospitalisationId);
        ExamenComplementaire examenComplementaire = hospitalisation.getExamenComplementaire();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            if (immunologic != null) {
                PiecesJointes piecesJointesDTO = piecesJointesService.findByObjectIdAndIdDocument
                        (examenComplementaire.getId(), ConstantSigps.TYPE_EXAM_IMMUNO_COMP).orElse(new PiecesJointes());
                piecesJointesDTO.setObjectId(examenComplementaire.getId());
                piecesJointesDTO.setDossier("examen_complementaire");
                piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_EXAM_IMMUNO_COMP);
                piecesJointesDTO.setNomFichier(immunologic.getName());
                piecesJointesService.savePiecesJointes(immunologic, objectMapper.writeValueAsString(piecesJointesDTO));
                return true;
            } else {
                throw new Exception("Hospitalisation n'existe pas.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean addExamImagerToHospitalisation(Long hospitalisationId, MultipartFile imager) throws Exception {
        Hospitalisation hospitalisation = findById(hospitalisationId);
        ExamenComplementaire examenComplementaire = hospitalisation.getExamenComplementaire();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            if (imager != null) {
                PiecesJointes piecesJointesDTO = piecesJointesService.findByObjectIdAndIdDocument
                        (examenComplementaire.getId(), ConstantSigps.TYPE_EXAM_IMG_COMP).orElse(new PiecesJointes());
                piecesJointesDTO.setObjectId(examenComplementaire.getId());
                piecesJointesDTO.setDossier("examen_complementaire");
                piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_EXAM_IMG_COMP);
                piecesJointesDTO.setNomFichier(imager.getName());
                piecesJointesService.savePiecesJointes(imager, objectMapper.writeValueAsString(piecesJointesDTO));
                return true;
            } else {
                throw new Exception("La hospitalisation n'existe pas.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean addExamHematologicToHospitalisation(Long hospitalisationId, MultipartFile hematologic) throws Exception {
        Hospitalisation hospitalisation = findById(hospitalisationId);
        ExamenComplementaire examenComplementaire = hospitalisation.getExamenComplementaire();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            if (hematologic != null) {
                PiecesJointes piecesJointesDTO = piecesJointesService.findByObjectIdAndIdDocument
                        (examenComplementaire.getId(), ConstantSigps.TYPE_EXAM_ANA_COMP).orElse(new PiecesJointes());
                piecesJointesDTO.setObjectId(examenComplementaire.getId());
                piecesJointesDTO.setDossier("examen_complementaire");
                piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_EXAM_ANA_COMP);
                piecesJointesDTO.setNomFichier(hematologic.getName());
                piecesJointesService.savePiecesJointes(hematologic, objectMapper.writeValueAsString(piecesJointesDTO));
                return true;
            } else {
                throw new Exception("L'hospitalisation n'existe pas.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean addProtocolMedicalTraitFileToHospitalisation(Long hospitalisationId, MultipartFile protocol) throws Exception {
        Hospitalisation hospitalisation = findById(hospitalisationId);
        TraitementMedical traitementMedical = hospitalisation.getTraitementMedical();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (protocol != null) {
                PiecesJointes piecesJointesDTO = new PiecesJointes();
                piecesJointesDTO.setObjectId(traitementMedical.getId());
                piecesJointesDTO.setDossier("traitement_medical");
                piecesJointesDTO.setTypeDocumentId(ConstantSigps.TYPE_PROTOCOLE_MEDIC);
                piecesJointesDTO.setNomFichier(protocol.getName());
                piecesJointesService.savePiecesJointes(protocol, objectMapper.writeValueAsString(piecesJointesDTO));
                return true;
            } else {
                throw new Exception("L'hospitalisation n'existe pas.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public int countHospitalisationHomme() {
        return hospitalisationRepository.countHospitalisationHomme();
    }

    @Override
    public int countHospitalisationFemme() {
        return hospitalisationRepository.countHospitalisationFemme();
    }

    @Override
    public long countHospitalisation() {
        return hospitalisationRepository.countHospitalisation();
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
