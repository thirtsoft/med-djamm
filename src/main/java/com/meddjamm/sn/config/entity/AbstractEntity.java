package com.meddjamm.sn.config.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
@ToString
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GenerationDeSequence")
    @SequenceGenerator(name = "GenerationDeSequence", sequenceName = "GEN_SEG_GEN", allocationSize = 1)
    private Long id;
    @Version
    private Long version;
}