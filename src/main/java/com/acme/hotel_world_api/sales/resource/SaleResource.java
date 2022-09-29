package com.acme.hotel_world_api.sales.resource;

import com.acme.hotel_world_api.sales.domain.model.AuditModel;

public class SaleResource extends AuditModel {
    private Long id;

    public Long getId(){
        return id;
    }

    public SaleResource setId(Long id){
        this.id=id;
        return this;
    }
}
