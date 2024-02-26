package com.meddjamm.sn.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDs {

    private Long id;

    private String titre;

    private File file;
}
