package com.acme.hotel_world_api.message.resources;

import com.acme.hotel_world_api.sales.domain.model.AuditModel;

public class ChatResource extends AuditModel{
    private Long id;
    
    public Long getId(){
        return id;
    }

    public ChatResource setId(Long id){
        this.id = id;
        return this;
    }
}
