package com.pasan.samples.leadcrm.repository;

import com.pasan.samples.leadcrm.repository.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}