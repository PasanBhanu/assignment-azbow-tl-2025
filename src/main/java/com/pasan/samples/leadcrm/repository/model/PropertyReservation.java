package com.pasan.samples.leadcrm.repository.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "property_reservation")
public class PropertyReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "property_id")
    private Property property;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "lead_id")
    private Lead lead;

    private Date reservationDate;

    private BigDecimal reservationFee;

    private Date expectedClosingDate;
}
