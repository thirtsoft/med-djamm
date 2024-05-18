package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "discussion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discussion extends AbstractAuditableEntity implements Serializable {

    @Column(columnDefinition = "TEXT")
    private String resume;

}
