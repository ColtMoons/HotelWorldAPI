package com.acme.hotel_world_api.security.resource;

public class HotelAdminResource {
    private Long id;

    private String name;

    private String username;

    private String password;
    
    private Long phone;

    public Long getId(){
        return id;
    }
    
    public HotelAdminResource setId(Long id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public HotelAdminResource setName(String name){
        this.name = name;
        return this;
    }

    public String getUsername(){
        return username;
    }

    public HotelAdminResource setUsername(String username){
        this.username = username;
        return this;
    }
    
    public String getPassword(){
        return password;
    }
    
    public HotelAdminResource setPassword(String password){
        this.password = password;
        return this;
    }
    
    public Long getPhone() {
        return phone;
    }
    
    public HotelAdminResource setPhone(Long phone) {
        this.phone = phone;
        return this;
    }
}
