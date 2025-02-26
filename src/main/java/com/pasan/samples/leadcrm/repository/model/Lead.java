package com.pasan.samples.leadcrm.repository.model;

import com.pasan.samples.leadcrm.util.LeadStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "Lead")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String contact;

    private String source;

    private Date inquiryDate;

    @Enumerated(EnumType.ORDINAL)
    private LeadStatus status;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    private String followUpStatus;

    private String preferredPropertyType;

    private BigDecimal budget;

    private String notes;
}
