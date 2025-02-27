package com.pasan.samples.leadcrm.repository;

import com.pasan.samples.leadcrm.repository.model.PropertyReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyReservationRepository extends JpaRepository<PropertyReservation, Integer> {

    Optional<PropertyReservation> findByLead_Id(Integer id);
}