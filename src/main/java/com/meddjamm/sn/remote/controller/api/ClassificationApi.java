package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.entity.Classification;
import com.meddjamm.sn.remote.model.ClassificationDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/classification")
public interface ClassificationApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClassificationDs> creerClassification(@RequestBody ClassificationDs classificationDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClassificationDs> updateClassification(@PathVariable Long id, @RequestBody ClassificationDs classificationDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClassificationDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ClassificationDs>> findAllClassifications();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteClassification(@PathVariable Long id);
}
