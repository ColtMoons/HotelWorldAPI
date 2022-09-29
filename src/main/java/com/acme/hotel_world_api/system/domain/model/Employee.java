package com.acme.hotel_world_api.system.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private Long dni;

    @NotNull
    private Long phoneNumber;

    @NotNull
    private String email;

    //relation
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonIgnore
    private Hotel hotel;

    //getters setters
    public Long getId(){
        return id;
    }

    public Employee setId(Long id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public Employee setName(String name){
        this.name = name;
        return this;
    }
    
    public Long getDni(){
        return dni;
    }

    public Employee setDni(Long dni){
        this.dni=dni;
        return this;
    }

    public Long getPhoneNumber(){
        return phoneNumber;
    }

    public Employee setPhoneNumber(Long phoneNumber){
        this.phoneNumber=phoneNumber;
        return this;
    }

    public String getEmail(){
        return email;
    }

    public Employee setEmail(String email){
        this.email = email;
        return this;
    }

    public Hotel getHotel(){
        return hotel;
    }

    public Employee setHotel(Hotel hotel){
        this.hotel = hotel;
        return this;
    }
}
