package com.pasan.samples.leadcrm.repository;

import com.pasan.samples.leadcrm.repository.model.PropertyReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyReservationRepository extends JpaRepository<PropertyReservation, Integer> {
}