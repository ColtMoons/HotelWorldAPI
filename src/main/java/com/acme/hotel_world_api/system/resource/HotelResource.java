package com.acme.hotel_world_api.system.resource;

public class HotelResource {
    private Long id;
    private String name;
    private String address;
    private Long phone;

    public Long getId(){
        return id;
    }
    public HotelResource setId(Long id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public HotelResource setName(String name){
        this.name = name;
        return this;
    }
    
    public String getAddress(){
        return address;
    }

    public HotelResource setAddress(String address){
        this.address = address;
        return this;
    }

    public Long getPhone(){
        return phone;
    }

    public HotelResource setPhone(Long phone){
        this.phone = phone;
        return this;
    }
}
