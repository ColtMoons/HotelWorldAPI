package com.acme.hotel_world_api.message.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveMessageGuestResource {
    @NotBlank
    @NotNull
    private String text;
    
    public String getText(){
        return text;
    }

    public SaveMessageGuestResource setText(String text){
        this.text = text;
        return this;
        
    }
}
