package com.biere.repositories;

import com.biere.entities.Biere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiereRepository extends JpaRepository<Biere, Integer>{
    
}
