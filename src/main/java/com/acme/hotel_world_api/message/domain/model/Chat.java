package com.acme.hotel_world_api.message.domain.model;

import javax.persistence.*;

import com.acme.hotel_world_api.sales.domain.model.AuditModel;
import com.acme.hotel_world_api.security.domain.model.Guest;
import com.acme.hotel_world_api.security.domain.model.HotelAdmin;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "chats")
public class Chat extends AuditModel{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guest_id", nullable = false)
    @JsonIgnore
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    @JsonIgnore
    private HotelAdmin admin;

    public Long getId(){
        return id;
    }

    public Chat setId(Long id){
        this.id = id;
        return this;
    }

    public Guest getGuest(){
        return guest;
    }
    public Chat setGuest(Guest guest){
        this.guest = guest;
        return this;
    }

    public HotelAdmin getAdmin(){
        return admin;
    }

    public Chat setAdmin(HotelAdmin admin){
        this.admin = admin;;
        return this;
    }

}
