package com.acme.hotel_world_api.system.domain.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.acme.hotel_world_api.sales.domain.model.Product;

@Entity
@Table(name = "hotels")
public class Hotel {
    
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String address;

    @NotNull
    private Long phone;
    //relation
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "hotel_products", 
    joinColumns = {@JoinColumn(name = "hotel_id")}, 
    inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;
    //getters and setters

    public Long getId(){
        return id;
    }

    public Hotel setId(Long id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public Hotel setName(String name){
        this.name = name;
        return this;
    }
    
    public String getAddress(){
        return address;
    }

    public Hotel setAddress(String address){
        this.address = address;
        return this;
    }

    public Long getPhone(){
        return phone;
    }

    public Hotel setPhone(Long phone){
        this.phone = phone;
        return this;
    }

    public List<Product> getProducts(){
        return products;
    }

    //bussiness logic

    public boolean containProduct(Product product){
        return this.getProducts().contains(product);
    }

    public Hotel addProduct(Product product){
        if(!containProduct(product)){
            this.getProducts().add(product);
        }
        return this;
    }

    public Hotel deleteProduct(Product product){
        if(containProduct(product)){
            this.getProducts().remove(product);
        }
        return this;
    }
}
