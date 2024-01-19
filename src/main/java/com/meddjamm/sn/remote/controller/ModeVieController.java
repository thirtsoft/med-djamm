package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.ModeVieAssembler;
import com.meddjamm.sn.entity.ModeVie;
import com.meddjamm.sn.remote.controller.api.ModeVieApi;
import com.meddjamm.sn.remote.model.ModeVieDs;
import com.meddjamm.sn.services.ModeVieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ModeVieController implements ModeVieApi {

    private final ModeVieService modeVieService;

    private final ModeVieAssembler modeVieAssembler;

    public ModeVieController(ModeVieService modeVieService,
                             ModeVieAssembler modeVieAssembler) {
        this.modeVieService = modeVieService;
        this.modeVieAssembler = modeVieAssembler;
    }

    @Override
    public void creerModeVie(ModeVieDs modeVieDs) {
        ModeVie modeVie = modeVieAssembler.assembleModeVieFromDs(modeVieDs);
        modeVieService.saveModeVie(modeVie);
    }

    @Override
    public void updateModeVie(Long id, ModeVieDs modeVieDs) throws Exception {
        ModeVie modeVie = modeVieAssembler.assembleModeVieFromDs(modeVieDs);
        modeVieService.updateModeVie(id, modeVie);
    }

    @Override
    public ResponseEntity<ModeVieDs> findById(Long id) {
        return new ResponseEntity<>(modeVieAssembler
                .assembleEntityToDs(modeVieService.findById(id)), HttpStatus.OK);
    }

    @Override
    public void deleteModeVie(Long id) {
        modeVieService.deleteModeVie(id);
    }
}
