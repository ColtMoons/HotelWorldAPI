package com.acme.hotel_world_api.message.domain.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.acme.hotel_world_api.sales.domain.model.AuditModel;
import com.acme.hotel_world_api.security.domain.model.HotelAdmin;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "admin_messages")
public class MessageAdmin extends AuditModel{
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
    @JoinColumn(name = "admin_id", nullable = false)
    @JsonIgnore
    private HotelAdmin admin;

    public Long getId(){
        return id;
    }

    public MessageAdmin setId(Long id){
        this.id = id;
        return this;
    }

    public String getText(){
        return text;
    }

    public MessageAdmin setText(String text){
        this.text = text;
        return this;
    }

    public HotelAdmin getAdmin(){
        return admin;
    }

    public MessageAdmin setAdmin(HotelAdmin admin){
        this.admin = admin;
        return this;
    }

    public Chat getChat(){
        return chat;
    }

    public MessageAdmin setChat(Chat chat){
        this.chat = chat;
        return this;
    }
}
