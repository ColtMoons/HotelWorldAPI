package com.acme.hotel_world_api.sales.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveProductResource {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Float price;


    public String getName(){
        return name;
    }

    public SaveProductResource setName(String name){
        this.name = name;
        return this;
    }

    public Float getPrice(){
        return price;
    }

    public SaveProductResource setPrice(Float price){
        this.price=price;
        return this;
    }
}
