package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.Hospitalisation;
import com.meddjamm.sn.dossiermedical.repository.HospitalisationRepository;
import com.meddjamm.sn.dossiermedical.services.HospitalisationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HospitalisationServiceImpl implements HospitalisationService {

    private final HospitalisationRepository hospitalisationRepository;

    public HospitalisationServiceImpl(HospitalisationRepository hospitalisationRepository) {
        this.hospitalisationRepository = hospitalisationRepository;
    }

    @Override
    public Hospitalisation saveHospitalisation(Hospitalisation hospitalisation) {
        hospitalisation.setActif(true);
        if (hospitalisation.getNumeroHospitalisation() == 0) {
            hospitalisation.setNumeroHospitalisation(createNumeroHospitalisation());
        }
        return hospitalisationRepository.save(hospitalisation);
    }

    @Override
    public Hospitalisation updateHospitalisation(Long id, Hospitalisation hospitalisation) {
        if (!hospitalisationRepository.existsById(id)) {
            log.info("Hospitalisation that id is " + id + "is not found");
        }
        hospitalisation.setId(id);
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

    private synchronized int createNumeroHospitalisation() {
        int nbr = 0;
        try {
            nbr = hospitalisationRepository.maxNumeroHospitalisation();

        } catch (Exception e) {
        }
        return (nbr + 1);
    }
}
