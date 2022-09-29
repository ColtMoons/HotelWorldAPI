package com.acme.hotel_world_api.reservation.resources;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class SaveReservationResource {
    
    @NotNull
    private Date date;

    public Date getDate(){
        return date;
    }

    public SaveReservationResource setDate(Date date){
        this.date = date;
        return this;
    }
}
