package com.meddjamm.sn.rh.services.Impl;

import com.meddjamm.sn.rh.entity.RendezVous;
import com.meddjamm.sn.rh.repository.RendezVousRepository;
import com.meddjamm.sn.rh.services.RendezVousService;
import com.meddjamm.sn.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RendezVousServiceImpl implements RendezVousService {

    private final RendezVousRepository rendezVousRepository;

    public RendezVousServiceImpl(RendezVousRepository rendezVousRepository) {
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        rendezVous.setActif(true);
        rendezVous.setCreateDate(new Date());
        rendezVous.setEtat(Constants.ETAT_PROGRAMME);
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public RendezVous updateRendezVous(Long id, RendezVous rendezVous) throws Exception {
        if (!rendezVousRepository.existsById(id)) {
            throw new Exception("This Rendez-vous is not found");
        }
        RendezVous rendezVousResult = rendezVousRepository.findRendezVousById(id);
        if (rendezVousResult == null) {
            throw new Exception("This Rendez-vous  is not found");
        }
        rendezVousResult.setDateRendezVous(rendezVous.getDateRendezVous());
        rendezVousResult.setHeure(rendezVous.getHeure());
        rendezVousResult.setMedecinId(rendezVous.getMedecinId());
        return rendezVousRepository.save(rendezVousResult);
    }

    @Override
    public RendezVous findById(Long id) {
        return rendezVousRepository.findRendezVousById(id);
    }

    @Override
    public List<RendezVous> findAllRendezVouss() {
        return rendezVousRepository.findAllRendezVous();
    }

    @Override
    public void deleteRendezVous(Long id) {
        RendezVous rendezVous = rendezVousRepository.findRendezVousById(id);
        rendezVous.setActif(false);
        rendezVousRepository.save(rendezVous);
    }

    @Override
    public List<RendezVous> findAllRendezVousByDoctorId(Long matricule) {
        return rendezVousRepository.findRendezVousByDoctorMatricule(matricule);
    }

    @Override
    public List<RendezVous> findTreeLatestRendezVousByPatient(Long patientId) {
        return rendezVousRepository.findTreeLatestRendezVousByPatient(patientId);
    }

    @Override
    public List<RendezVous> findRendezVousDuJours() {
        return rendezVousRepository.findAllRendezVousDay();
    }

    @Override
    public List<RendezVous> findAllRendezVousOfDoctorInMonth(Long matricule) {
        return rendezVousRepository.findAllRendezVousOfDoctorInMonth(matricule);
    }

    @Override
    public void deplacezRendezVous(RendezVous rendezVousDeplace) {
        if (rendezVousDeplace == null) {
            log.info("Pas de rendez à déplacer");
        }
        assert rendezVousDeplace != null;
        rendezVousRepository.save(rendezVousDeplace);
    }
}
