package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.Discussion;

import java.util.List;

public interface DiscussionService {

    Discussion saveDiscussion(Discussion discussion);

    Discussion updateDiscussion(Long id, Discussion discussion) throws Exception;

    Discussion findById(Long id);

    List<Discussion> findAllDiscussions();

    void deleteDiscussion(Long id);
}
