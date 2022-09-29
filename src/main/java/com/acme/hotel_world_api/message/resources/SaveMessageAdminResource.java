package com.acme.hotel_world_api.message.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveMessageAdminResource {
    @NotBlank
    @NotNull
    private String text;
    
    public String getText(){
        return text;
    }

    public SaveMessageAdminResource setText(String text){
        this.text = text;
        return this;
        
    }
}
