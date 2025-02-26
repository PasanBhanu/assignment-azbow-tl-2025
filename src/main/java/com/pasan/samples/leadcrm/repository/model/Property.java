package com.pasan.samples.leadcrm.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "property")
public class Property {

    @Id
    private Integer id;

    private String name;
}
