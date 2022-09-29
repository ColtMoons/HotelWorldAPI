package com.acme.hotel_world_api.sales.resource;

public class ProductResource {
   
    private Long id;

    private String name;

    private Float price;

    //getters setters
    public Long getId(){
        return id;
    }

    public ProductResource setId(Long id){
        this.id=id;
        return this;
    }

    public String getName(){
        return name;
    }

    public ProductResource setName(String name){
        this.name = name;
        return this;
    }

    public Float getPrice(){
        return price;
    }

    public ProductResource setPrice(Float price){
        this.price=price;
        return this;
    }
}
