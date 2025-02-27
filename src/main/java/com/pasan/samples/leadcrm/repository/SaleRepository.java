package com.pasan.samples.leadcrm.repository;

import com.pasan.samples.leadcrm.repository.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    Optional<Sale> findByPropertyReservation_Id(Integer id);
}