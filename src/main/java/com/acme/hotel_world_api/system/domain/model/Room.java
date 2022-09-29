package com.acme.hotel_world_api.system.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rooms")
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long roomNumber;

    @NotNull
    private String roomType;

    @NotNull
    private Float price;
    //relation
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonIgnore
    private Hotel hotel;

    //getters and setters

    public Long getId(){
        return id;
    }

    public Room setId(Long id){
        this.id = id;
        return this;
    }

    public Long getRoomNumber(){
        return roomNumber;
    }

    public Room setRoomNumber(Long roomNumber){
        this.roomNumber = roomNumber;
        return this;
    }

    public String getRoomType(){
        return roomType;
    }

    public Room setRoomType(String roomType){
        this.roomType = roomType;
        return this;
    }

    public Float getPrice(){
        return price;
    }

    public Room setPrice(Float price){
        this.price = price;
        return this;
    }

    public Hotel getHotel(){
        return hotel;
    }

    public Room setHotel(Hotel hotel){
        this.hotel = hotel;
        return this;
    }
}
