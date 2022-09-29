package com.acme.hotel_world_api.system.resource;

public class RoomResource {
    private Long id;

    private Long roomNumber;

    private String roomType;

    private Float price;

    public Long getId(){
        return id;
    }

    public RoomResource setId(Long id){
        this.id = id;
        return this;
    }

    public Long getRoomNumber(){
        return roomNumber;
    }

    public RoomResource setRoomNumber(Long roomNumber){
        this.roomNumber = roomNumber;
        return this;
    }

    public String getRoomType(){
        return roomType;
    }

    public RoomResource setRoomType(String roomType){
        this.roomType = roomType;
        return this;
    }

    public Float getPrice(){
        return price;
    }

    public RoomResource setPrice(Float price){
        this.price = price;
        return this;
    }
}
