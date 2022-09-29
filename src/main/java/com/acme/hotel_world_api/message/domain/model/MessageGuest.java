package com.acme.hotel_world_api.message.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.acme.hotel_world_api.sales.domain.model.AuditModel;
import com.acme.hotel_world_api.security.domain.model.Guest;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "guest_messages")
public class MessageGuest extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_id", nullable = false)
    @JsonIgnore
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guest_id", nullable = false)
    @JsonIgnore
    private Guest guest;

    public Long getId(){
        return id;
    }

    public MessageGuest setId(Long id){
        this.id = id;
        return this;
    }

    public String getText(){
        return text;
    }

    public MessageGuest setText(String text){
        this.text = text;
        return this;
    }

    public Guest getGuest(){
        return guest;
    }

    public MessageGuest setGuest(Guest guest){
        this.guest = guest;
        return this;
    }

    public Chat getChat(){
        return chat;
    }

    public MessageGuest setChat(Chat chat){
        this.chat = chat;
        return this;
    }
}
