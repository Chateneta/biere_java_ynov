package com.biere.entities;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "users"})
public class Biere {
    @Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull @Column
    String name;

    @Column(name="description")
    String desc;

    @NotNull @Column
    String date;

    @NotNull @Column
    Float degree;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "users_bieres", 
        joinColumns = @JoinColumn(name = "biere_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_username")
    )
    List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getDegree() {
        return degree;
    }

    public void setDegree(Float degree) {
        this.degree = degree;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(User u) {
        this.users.add(u);
    }
}
