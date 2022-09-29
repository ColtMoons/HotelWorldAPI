package com.acme.hotel_world_api.security.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "guests")
public class Guest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String username;

    @NotNull
    private String password;

    //relations

    //setters y getters

    public Long getId(){
        return id;
    }

    public Guest setId(Long id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public Guest setName(String name){
        this.name = name;
        return this;
    }

    public String getUsername(){
        return username;
    }

    public Guest setUsername(String username){
        this.username = username;
        return this;
    }

    public String getPassword(){
        return password;
    }

    public Guest setPassword(String password){
        this.password = password;
        return this;
    }
}
