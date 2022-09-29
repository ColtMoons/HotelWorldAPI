package com.acme.hotel_world_api.security.resource;

public class GuestResource {
    private Long id;

    private String name;

    private String username;

    private String password;

    public Long getId(){
        return id;
    }

    public GuestResource setId(Long id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public GuestResource setName(String name){
        this.name = name;
        return this;
    }

    public String getUsername(){
        return username;
    }

    public GuestResource setUsername(String username){
        this.username = username;
        return this;
    }

    public String getPassword(){
        return password;
    }

    public GuestResource setPassword(String password){
        this.password = password;
        return this;
    }
}
