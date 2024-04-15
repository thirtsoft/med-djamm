package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.RendezVous;
import com.meddjamm.sn.dossiermedical.repository.RendezVousRepository;
import com.meddjamm.sn.dossiermedical.services.RendezVousService;
import com.meddjamm.sn.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
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
        rendezVousResult.setId(id);
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
    public List<RendezVous> findAllRendezVousByDoctorId(String matricule) {
        return rendezVousRepository.findRendezVousByDoctorMatricule(matricule);
    }
}
