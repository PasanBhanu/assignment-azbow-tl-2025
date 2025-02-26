package com.pasan.samples.leadcrm.repository;

import com.pasan.samples.leadcrm.repository.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Integer> {
}