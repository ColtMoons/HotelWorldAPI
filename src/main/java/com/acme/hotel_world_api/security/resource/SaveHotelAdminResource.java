package com.acme.hotel_world_api.security.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveHotelAdminResource {
   
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String username;

    @NotBlank
    @NotNull
    @Size(min = 8)
    private String password;
    
    @NotNull
    private Long phone;

    public String getName(){
        return name;
    }

    public SaveHotelAdminResource setName(String name){
        this.name = name;
        return this;
    }

    public String getUsername(){
        return username;
    }

    public SaveHotelAdminResource setUsername(String username){
        this.username = username;
        return this;
    }
    
    public String getPassword(){
        return password;
    }
    
    public SaveHotelAdminResource setPassword(String password){
        this.password = password;
        return this;
    }
    
    public Long getPhone() {
        return phone;
    }
    
    public SaveHotelAdminResource setPhone(Long phone) {
        this.phone = phone;
        return this;
    }
}
