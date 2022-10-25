package com.acme.hotel_world_api.sales.domain.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.acme.hotel_world_api.system.domain.model.Hotel;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double price;

    //relation
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "products")
    private List<Hotel> hotels;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "products_tags", 
    joinColumns = {@JoinColumn(name = "product_id")}, 
    inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "products")
    private List<Sale> sales;

    //getters setters
    public Long getId(){
        return id;
    }

    public Product setId(Long id){
        this.id=id;
        return this;
    }

    public String getName(){
        return name;
    }

    public Product setName(String name){
        this.name = name;
        return this;
    }

    public Double getPrice(){
        return price;
    }

    public Product setPrice(Double price){
        this.price=price;
        return this;
    }
    public Product setTags(List<Tag> tags){
        this.tags = tags;
        return this;
    }

    public Product setHotels(List<Hotel> hotels){
        this.hotels = hotels;
        return this;
    }

    public Product setSales(List<Sale> sales){
        this.sales = sales;
        return this;
    }
    public List<Tag> getTags(){
        return tags;
    }
    public List<Hotel> getHotels(){
        return hotels;
    }
    public List<Sale> getSales(){
        return sales;
    }


    //bussiness logic
    public boolean isTaggedWith(Tag tag){
        return this.getTags().contains(tag);
    }

    public Product tagWith(Tag tag){
         if(!isTaggedWith(tag)){
             this.getTags().add(tag);
         }
         return this;
    }

    public Product unTagWith(Tag tag){
        if(this.isTaggedWith(tag)){
            this.getTags().remove(tag);
        }
        return this;
    }
}
