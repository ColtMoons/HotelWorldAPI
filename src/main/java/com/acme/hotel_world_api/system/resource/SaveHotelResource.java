package com.acme.hotel_world_api.system.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveHotelResource {
    
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;
    
    @NotNull
    @NotBlank
    @Size(max = 250)
    private String address;
    
    @NotNull
    private Long phone;

    public String getName(){
        return name;
    }

    public SaveHotelResource setName(String name){
        this.name = name;
        return this;
    }
    
    public String getAddress(){
        return address;
    }

    public SaveHotelResource setAddress(String address){
        this.address = address;
        return this;
    }

    public Long getPhone(){
        return phone;
    }

    public SaveHotelResource setPhone(Long phone){
        this.phone = phone;
        return this;
    }
}
