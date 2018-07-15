package com.crp.qa.qaAuthorization.repository; 

import org.springframework.data.jpa.repository.JpaRepository;

import com.crp.qa.qaAuthorization.domain.pojo.QaSysGroupRights;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface QaSysGroupRightsRepository extends JpaRepository<QaSysGroupRights,Integer> {

}
