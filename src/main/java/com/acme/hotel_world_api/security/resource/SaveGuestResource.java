package com.acme.hotel_world_api.security.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveGuestResource {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    @Size(min = 8)
    private String password;

    public String getName(){
        return name;
    }

    public SaveGuestResource setName(String name){
        this.name = name;
        return this;
    }

    public String getUsername(){
        return username;
    }

    public SaveGuestResource setUsername(String username){
        this.username = username;
        return this;
    }

    public String getPassword(){
        return password;
    }

    public SaveGuestResource setPassword(String password){
        this.password = password;
        return this;
    }
}
