package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.Examen;

import java.util.List;

public interface ExamenService {

    Examen saveExamen(Examen examen);

    Examen updateExamen(Long id, Examen examen) throws Exception;

    Examen findById(Long id);

    List<Examen> findAllExamens();

    void deleteExamen(Long id);
}
