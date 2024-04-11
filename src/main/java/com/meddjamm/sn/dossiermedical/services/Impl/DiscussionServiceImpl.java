package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.entity.Discussion;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.DiscussionRepository;
import com.meddjamm.sn.dossiermedical.services.DiscussionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DiscussionServiceImpl implements DiscussionService {

    private final DiscussionRepository discussionRepository;

    private final CircuitPatientRepository circuitPatientRepository;

    public DiscussionServiceImpl(DiscussionRepository discussionRepository, CircuitPatientRepository circuitPatientRepository) {
        this.discussionRepository = discussionRepository;
        this.circuitPatientRepository = circuitPatientRepository;
    }

    @Override
    public Discussion saveDiscussion(Discussion discussion) {
        discussion.setActif(true);
        CircuitPatient circuitPatient = circuitPatientRepository.findCircuitPatientById(discussion.getCircuitPatientId());
        circuitPatient.setType("Discussion");
        circuitPatientRepository.save(circuitPatient);
        discussion.setCircuitPatientId(circuitPatient.getId());
        discussion.setCircuitPatient(circuitPatient);
        return discussionRepository.save(discussion);
    }

    @Override
    public Discussion updateDiscussion(Long id, Discussion discussion) throws Exception {
        if (!discussionRepository.existsById(id)) {
            log.info("Discussion that this id " + id + "not found");
        }
        discussion.setId(id);
        return discussionRepository.save(discussion);
    }

    @Override
    public Discussion findById(Long id) {
        return discussionRepository.findDiscussionById(id);
    }

    @Override
    public List<Discussion> findAllDiscussions() {
        return discussionRepository.findAllDiscussions();
    }

    @Override
    public void deleteDiscussion(Long id) {
        Discussion discussion = discussionRepository.findDiscussionById(id);
        discussion.setActif(false);
        discussionRepository.save(discussion);
    }

    @Override
    public List<Discussion> findDiscussionByPatientId(String code) {
        return discussionRepository.findDiscussionByPatientId(code);
    }
}
