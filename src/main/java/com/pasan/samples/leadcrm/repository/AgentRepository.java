package com.pasan.samples.leadcrm.repository;

import com.pasan.samples.leadcrm.repository.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
}