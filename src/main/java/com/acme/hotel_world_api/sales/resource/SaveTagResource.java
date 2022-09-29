package com.acme.hotel_world_api.sales.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveTagResource {
    @NotNull
    @Size(max = 100)
    @NotBlank
    private String name;

    public String getName(){
        return name;
    }

    public SaveTagResource setName(String name){
        this.name = name;
        return this;
    }
}
