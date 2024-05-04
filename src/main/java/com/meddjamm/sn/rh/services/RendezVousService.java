package com.meddjamm.sn.rh.services;

import com.meddjamm.sn.rh.entity.RendezVous;

import java.util.List;

public interface RendezVousService {

    RendezVous saveRendezVous(RendezVous rendezVous);

    RendezVous updateRendezVous(Long id, RendezVous rendezVous) throws Exception;

    RendezVous findById(Long id);

    List<RendezVous> findAllRendezVouss();

    void deleteRendezVous(Long id);

    List<RendezVous> findAllRendezVousByDoctorId(Long matricule);

}
