package com.acme.hotel_world_api.message.resources;

import com.acme.hotel_world_api.sales.domain.model.AuditModel;

public class MessageGuestResource extends AuditModel {
    private Long id;
    private String text;

    public Long getId() {
        return id;
    }

    public MessageGuestResource setId(Long id) {
        this.id = id;
        return this;
    }


    public String getText() {
        return text;
    }

    public MessageGuestResource setText(String text) {
        this.text = text;
        return this;
    }

}
