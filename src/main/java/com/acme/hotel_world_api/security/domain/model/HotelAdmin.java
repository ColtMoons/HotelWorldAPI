package com.acme.hotel_world_api.security.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.acme.hotel_world_api.system.domain.model.Hotel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "administrators")
public class HotelAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String username;

    @NotNull
    private String password;
    
    @NotNull
    private Long phone;

    //relations

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonIgnore
    private Hotel hotel;

    //getters setters
    
    
    public Long getId(){
        return id;
    }
    
    public HotelAdmin setId(Long id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public HotelAdmin setName(String name){
        this.name = name;
        return this;
    }

    public String getUsername(){
        return username;
    }

    public HotelAdmin setUsername(String username){
        this.username = username;
        return this;
    }
    
    public String getPassword(){
        return password;
    }
    
    public HotelAdmin setPassword(String password){
        this.password = password;
        return this;
    }
    
    public Long getPhone() {
        return phone;
    }
    
    public HotelAdmin setPhone(Long phone) {
        this.phone = phone;
        return this;
    }

    public Hotel getHotel(){
        return hotel;
    }

    public HotelAdmin setHotel(Hotel hotel){
        this.hotel = hotel;
        return this;
    }
    
}
