package com.acme.hotel_world_api.system.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.hotel_world_api.system.domain.model.Hotel;
import com.acme.hotel_world_api.system.domain.service.HotelService;
import com.acme.hotel_world_api.system.resource.HotelResource;
import com.acme.hotel_world_api.system.resource.SaveHotelResource;


@RestController
@RequestMapping("/api")
public class HotelsController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/hotels")
    public Page<HotelResource> getAllHotel(Pageable pageable){
        Page<Hotel> hotelPage= hotelService.getAllHotel(pageable);
        List<HotelResource> resources = hotelPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/hotels/{hotelId}")
    public HotelResource getHotelById(@PathVariable Long hotelId){
        return convertToResource(hotelService.getHotelById(hotelId));
    }

    @PostMapping("/hotels")
    public HotelResource createHotel(@Valid @RequestBody SaveHotelResource resource){
        Hotel hotel = convertToEntity(resource);
        return convertToResource(hotelService.createHotel(hotel));
    }

    @PutMapping("/hotels/{hotelId}")
    public HotelResource updateHotelResource(@PathVariable Long hotelId, @Valid @RequestBody SaveHotelResource resource){
        Hotel hotel = convertToEntity(resource);
        return convertToResource(hotelService.updateHotel(hotelId, hotel));
    }

    @DeleteMapping("/hotels/{hotelId}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long hotelId){
        return hotelService.deleteHotel(hotelId);
    }
    
    private Hotel convertToEntity(SaveHotelResource resource){
        return mapper.map(resource, Hotel.class);
    }

    private HotelResource convertToResource(Hotel entity){
        return mapper.map(entity, HotelResource.class);
    }
}
