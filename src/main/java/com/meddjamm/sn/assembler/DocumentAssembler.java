package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.Document;
import com.meddjamm.sn.remote.model.DocumentDs;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DocumentAssembler {

    public List<DocumentDs> createListDocumentDs(Set<Document> documents) {
        if (documents == null)
            return Collections.emptyList();
        List<DocumentDs> dtos = new ArrayList<>();
        for (Document document : documents) {
            dtos.add(assembleEntityToDs(document));
        }
        return dtos;
    }

    public Set<Document> createSetDocuments(List<DocumentDs> documentDs) {
        if (documentDs == null)
            return null;
        Set<Document> actions = new HashSet<>();
        for (DocumentDs dto : documentDs)
            if (dto != null)
                actions.add(assembleDocumentFromDs(dto));
        return actions;
    }

    public DocumentDs assembleEntityToDs(Document document) {
        DocumentDs documentDs = new DocumentDs();
        documentDs.setId(document.getId());
        documentDs.setTitre(document.getTitre());
        documentDs.setFile(document.getFile());
        return documentDs;
    }

    public Document assembleDocumentFromDs(DocumentDs documentDs) {
        Document document = new Document();
        document.setId(documentDs.getId());
        document.setTitre(documentDs.getTitre());
        document.setFile(documentDs.getFile());
        return document;
    }

}
