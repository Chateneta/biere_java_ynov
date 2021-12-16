package com.biere.services;

import java.util.List;

import com.biere.entities.Biere;
import com.biere.repositories.BiereRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiereService {
    @Autowired
    private BiereRepository biereRepository;
    
    public Biere createOrUpdate(Biere s) { 
        return biereRepository.save(s); 
    } 
       
    public Biere getBiereById(Integer id) { 
        return biereRepository.findById(id).orElse(null); 
    }

    public List<Biere> getAllBiere() { 
        return biereRepository.findAll(); 
    }

    public void deletBiereById(Integer id){
        biereRepository.deleteById(id);
    }
}
