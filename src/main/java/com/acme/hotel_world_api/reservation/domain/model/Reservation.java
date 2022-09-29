package com.acme.hotel_world_api.reservation.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.acme.hotel_world_api.security.domain.model.Guest;
import com.acme.hotel_world_api.system.domain.model.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reservations")
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guest_id", nullable = false)
    @JsonIgnore
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @JsonIgnore
    private Room room;

    public Long getId(){
        return id;
    }

    public Reservation setId(Long id){
        this.id = id;
        return this;
    }

    public Date getDate(){
        return date;
    }

    public Reservation setDate(Date date){
        this.date = date;
        return this;
    }

    public Guest getGuest(){
        return guest;
    }

    public Reservation setGuest(Guest guest){
        this.guest = guest;
        return this;
    }

    public Room getRoom(){
        return room;
    }

    public Reservation setRoom(Room room){
        this.room = room;
        return this;
    }
}
