package com.meddjamm.sn.config.remote.model;

import lombok.Builder;

@Builder
public record ValidationRequest(String code) {
}