package com.zinkwork.atm.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zinkwork.atm.entity.AtmEntity;
import com.zinkwork.atm.entity.NoteEntity;

public interface NoteRepository extends JpaRepository<NoteEntity, Integer> {

	Set<NoteEntity> findByAtmEntity(AtmEntity atmObj);

}
