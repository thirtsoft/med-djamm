package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.Hospitalisation;
import com.meddjamm.sn.repository.HospitalisationRepository;
import com.meddjamm.sn.services.HospitalisationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        hospitalisation.setCreatedDate(new Date());
        return hospitalisationRepository.save(hospitalisation);
    }

    @Override
    public Hospitalisation updateHospitalisation(Long id, Hospitalisation hospitalisation) throws Exception {
        if (!hospitalisationRepository.existsById(id)) {
            log.info("Hospitalisation that id " + id + "is not found");
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
    public void deleteHospitalisation(Long id) {
        Hospitalisation hospitalisation = findById(id);
        hospitalisation.setActif(false);
        hospitalisationRepository.save(hospitalisation);
    }
}
