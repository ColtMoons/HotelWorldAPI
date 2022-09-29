package com.acme.hotel_world_api.sales.domain.model;

import java.util.List;

import javax.persistence.*;
import com.acme.hotel_world_api.system.domain.model.Hotel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sales")
public class Sale extends AuditModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonIgnore
    private Hotel hotel;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sale_products", 
    joinColumns = {@JoinColumn(name = "sale_id")}, 
    inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;

    public Long getId(){
        return id;
    }

    public Sale setId(Long id){
        this.id=id;
        return this;
    }

    public Hotel getHotel(){
        return hotel;
    }
    public Sale setHotel(Hotel hotel){
        this.hotel=hotel;
        return this;
    }

    public List<Product> getProducts(){
        return products;
    }

    //bussiness logic 
    public boolean containsProduct(Product product){
        return this.getProducts().contains(product);
    }

    public Sale addProduct(Product product){
        if(!containsProduct(product)){
            this.getProducts().add(product);
        }
        return this;
    }

    public Sale deleteProduct(Product product){
        if(containsProduct(product)){
            this.getProducts().remove(product);
        }
        return this;
    }
}
