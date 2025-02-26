package com.pasan.samples.leadcrm.repository;

import com.pasan.samples.leadcrm.repository.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
}