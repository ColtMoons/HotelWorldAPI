package com.acme.hotel_world_api.system.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveRoomResource {

    @NotNull
    private Long roomNumber;

    @NotNull
    @NotBlank
    private String roomType;

    @NotNull
    private Float price;


    public Long getRoomNumber(){
        return roomNumber;
    }

    public SaveRoomResource setRoomNumber(Long roomNumber){
        this.roomNumber = roomNumber;
        return this;
    }

    public String getRoomType(){
        return roomType;
    }

    public SaveRoomResource setRoomType(String roomType){
        this.roomType = roomType;
        return this;
    }

    public Float getPrice(){
        return price;
    }

    public SaveRoomResource setPrice(Float price){
        this.price = price;
        return this;
    }
}
