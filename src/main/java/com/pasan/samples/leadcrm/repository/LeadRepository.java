package com.pasan.samples.leadcrm.repository;

import com.pasan.samples.leadcrm.repository.model.Lead;
import com.pasan.samples.leadcrm.util.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

public interface LeadRepository extends JpaRepository<Lead, Integer> {

    List<Lead> findByStatusInOrAgent_Id(Set<LeadStatus> statuses, @Nullable Integer id);
}