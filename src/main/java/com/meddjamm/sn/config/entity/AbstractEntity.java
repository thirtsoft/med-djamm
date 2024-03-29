package com.meddjamm.sn.config.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.SEQUENCE;

@MappedSuperclass
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "GenerationDeSequence")
    @SequenceGenerator(name = "GenerationDeSequence", sequenceName = "GEN_SEG_GEN", allocationSize = 1)
    private Long id;
    @Version
    private Long version;
}