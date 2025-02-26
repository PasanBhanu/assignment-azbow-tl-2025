package com.pasan.samples.leadcrm.repository.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "agent")
public class Agent {

    @Id
    private Integer id;

    private String name;
}
