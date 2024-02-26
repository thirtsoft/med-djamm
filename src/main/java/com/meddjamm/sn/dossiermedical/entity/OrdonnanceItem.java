package com.meddjamm.sn.dossiermedical.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "ordonnance_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdonnanceItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "medicament_uid")
    private String code;

    private String psologie;

    private String nbrePrise;

}