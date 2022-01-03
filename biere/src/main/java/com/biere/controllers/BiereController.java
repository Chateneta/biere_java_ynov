package com.biere.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.biere.entities.Biere;
import com.biere.entities.User;
import com.biere.services.BiereService;
import com.biere.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class BiereController {
    
    @Autowired
    private BiereService biereService;

    @Autowired
    private UserService userService;

    public Biere store(Biere s, Biere info){
        s.setName(info.getName());
        s.setDesc(info.getDesc());
        s.setDate(info.getDate());
        s.setDegree(info.getDegree());
        return biereService.createOrUpdate(s);
    }

    @Operation(summary = "Return biere from ID")
    @RequestMapping(path="/biere", method = RequestMethod.GET)
    public Biere get(@RequestParam(value = "id") Integer id){
        return biereService.getBiereById(id);
    }

    @Operation(summary = "Edit Biere")
    @RequestMapping(path="/biere", method = RequestMethod.PUT)
    public Biere edit(@RequestBody Biere biere){
        Biere s = null;
        if (biere.getId() != null) {
            s = biereService.getBiereById(biere.getId());
        }
        if(s == null){
            s = new Biere();
        }
        return store(s, biere);
    }

    @Operation(summary = "Return all biere")
    @RequestMapping(path="/biere/all", method = RequestMethod.GET)
    public List<Biere> getAll(){
        return biereService.getAllBiere();
    }

    @Operation(summary = "Delete biere from id")
    @RequestMapping(path="/biere/{id}", method = RequestMethod.DELETE)
    public void getAll(@PathVariable(value="id")Integer id){
        biereService.deleteBiereById(id);
    }

    @Operation(summary = "Return PDF from id")
    @RequestMapping(path="/biere/pdf/{id}", method = RequestMethod.GET)
    public String getPDF(@PathVariable(value="id")Integer id){
        return biereService.getPDFBiere(id);
    }
}
