package com.biere.services;

import java.io.IOException;
import java.util.List;

import com.biere.entities.Biere;
import com.biere.pdf.PDF;
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

    public void deleteBiereById(Integer id){
        biereRepository.deleteById(id);
    }

    public String getPDFBiere(Integer id) {
        Biere biere = getBiereById(id);
        PDF pdf = new PDF(biere);
        return pdf.getFilePath();
    }
}
