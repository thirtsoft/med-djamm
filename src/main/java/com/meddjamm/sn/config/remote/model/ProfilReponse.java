package com.meddjamm.sn.config.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfilReponse {
    private String code;
    private List<ActionListResponse> actionListResponses;
}