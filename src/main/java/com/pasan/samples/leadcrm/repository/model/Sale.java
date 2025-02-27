package com.pasan.samples.leadcrm.repository.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "property_reservation_id")
    private PropertyReservation propertyReservation;

    private Date saleDate;

    private BigDecimal finalSalePrice;

    private String commissionDetails;
}
