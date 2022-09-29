package com.acme.hotel_world_api.reservation.resources;

import java.util.Date;

public class ReservationResource {
    private Long id;

    private Date date;

    public Long getId(){
        return id;
    }

    public ReservationResource setId(Long id){
        this.id = id;
        return this;
    }

    public Date getDate(){
        return date;
    }

    public ReservationResource setDate(Date date){
        this.date = date;
        return this;
    }

}
