package com.biere.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table
// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable{
    @Id @NotNull @Column(name="username",unique=true)
    String username;

    @NotNull @Column(name="firstname")
    String firstname;

    @NotNull @Column(name="lastname")
    String lastname;

    @NotNull @Column(name="phone")
    String phone;

    @NotNull @Column(name="password")
    String password;

    @JsonIgnore
    @ManyToMany
    @JsonManagedReference
    @JoinTable(
        name = "users_bieres", 
        joinColumns = @JoinColumn(name = "user_username"), 
        inverseJoinColumns = @JoinColumn(name = "biere_id")
    )
    List<Biere> bieres;
    
    public void setUsername(String name){
        this.username = name;
    }
    public void setFirstname(String name){
        this.firstname = name;
    }
    public void setLastname(String name){
        this.lastname = name;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getUsername() {
        return username;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getPhone() {
        return phone;
    }

    public List<Biere> getBieres(){
        return bieres;
    }

    public void setBieres(Biere s){
        this.bieres.add(s);
    }

    public void lessBieres(Biere s){
        this.bieres.remove(s);
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
