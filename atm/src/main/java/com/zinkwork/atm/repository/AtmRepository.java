package com.zinkwork.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zinkwork.atm.entity.AtmEntity;

@Repository
public interface AtmRepository extends JpaRepository<AtmEntity, Integer> {

}
