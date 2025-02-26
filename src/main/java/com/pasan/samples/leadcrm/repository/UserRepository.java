package com.pasan.samples.leadcrm.repository;

import com.pasan.samples.leadcrm.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
