package com.acme.hotel_world_api.sales.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.hotel_world_api.sales.domain.model.Sale;
import com.acme.hotel_world_api.sales.domain.service.SaleService;
import com.acme.hotel_world_api.sales.resource.SaleResource;

@RestController
@RequestMapping("/api")
public class SaleProductsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SaleService saleService;
    
    @PostMapping("/sales/{saleId}/products/{productId}")
    public SaleResource assingSaleProduct(@PathVariable Long saleId, @PathVariable Long productId){
        return convertToResource(saleService.addProduct(saleId, productId));
    }

    @DeleteMapping("/sales/{saleId}/products/{productId}")
    public SaleResource unassingSaleProduct(@PathVariable Long saleId, @PathVariable Long productId){
        return convertToResource(saleService.deleteProduct(saleId, productId));
    }


    private SaleResource convertToResource(Sale entity){
        return mapper.map(entity, SaleResource.class);
    }
}
