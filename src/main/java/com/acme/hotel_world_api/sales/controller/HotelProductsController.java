package com.acme.hotel_world_api.sales.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.hotel_world_api.system.domain.model.Hotel;
import com.acme.hotel_world_api.system.domain.service.HotelService;
import com.acme.hotel_world_api.system.resource.HotelResource;

@RestController
@RequestMapping("/api")
public class HotelProductsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HotelService hotelService;

    @PostMapping("/hotels/{hotelId}/products/{productId}")
    public HotelResource assignHotelProduct(@PathVariable Long hotelId, @PathVariable Long productId){
        return convertToResource(hotelService.assingHotelProduct(hotelId, productId));
    }

    @DeleteMapping("/hotels/{hotelId}/products/{productId}")
    public HotelResource unassignHotelProduct(@PathVariable Long hotelId, @PathVariable Long productId){
        return convertToResource(hotelService.unassingHotelProduct(hotelId, productId));
    }


    public HotelResource convertToResource(Hotel entity){
        return mapper.map(entity, HotelResource.class);
    }
}
