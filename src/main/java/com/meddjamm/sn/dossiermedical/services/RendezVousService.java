package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.RendezVous;

import java.util.List;

public interface RendezVousService {

    RendezVous saveRendezVous(RendezVous rendezVous);

    RendezVous updateRendezVous(Long id, RendezVous rendezVous) throws Exception;

    RendezVous findById(Long id);

    List<RendezVous> findAllRendezVouss();

    void deleteRendezVous(Long id);

    List<RendezVous> findRendezVousByDoctorId(String matricule);

}
