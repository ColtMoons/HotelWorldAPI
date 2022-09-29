package com.acme.hotel_world_api.security.controller;

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

import com.acme.hotel_world_api.security.domain.model.HotelAdmin;
import com.acme.hotel_world_api.security.domain.service.HotelAdminService;
import com.acme.hotel_world_api.security.resource.HotelAdminResource;
import com.acme.hotel_world_api.security.resource.SaveHotelAdminResource;

@RestController
@RequestMapping("/api")
public class HotelAdminsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private HotelAdminService hotelAdminService;

    @GetMapping("/hotels/{hotelId}/admins")
    public Page<HotelAdminResource> getAllHotelAdminsByHotelId(@PathVariable Long hotelId, Pageable pageable){
        Page<HotelAdmin> hotelPage = hotelAdminService.getAllHotelAdminByHotelId(hotelId, pageable);

        List<HotelAdminResource> resources = hotelPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("hotels/{hotelId}/admins/{hotelAdminId}")
    public HotelAdminResource getHotelAdminByIdAndHotelId(@PathVariable Long hotelId, @PathVariable Long hotelAdminId){
        return convertToResource(hotelAdminService.getHotelAdminByIdAndHotelId(hotelId, hotelAdminId));
    }

    @PostMapping("/hotels/{hotelId}/admins")
    public HotelAdminResource createHotelAdmin(@PathVariable Long hotelId, @Valid @RequestBody SaveHotelAdminResource resource){
        return convertToResource(hotelAdminService.createHotelAdmin(hotelId, convertToEntity(resource)));
    }

    @PutMapping("hotels/{hotelId}/admins/{hotelAdminId}")
    public HotelAdminResource updateHotelAdminResource(@PathVariable Long hotelId, @PathVariable Long hotelAdminId, @Valid @RequestBody SaveHotelAdminResource resource){
        return convertToResource(hotelAdminService.updateHotelAdmin(hotelId, hotelAdminId, convertToEntity(resource)));
    }

    @DeleteMapping("hotels/{hotelId}/admins/{hotelAdminId}")
    public ResponseEntity<?> deleteHotelAdmin(@PathVariable Long hotelId, @PathVariable Long hotelAdminId){
        return hotelAdminService.deleteHotelAdmin(hotelId, hotelAdminId);
    }

    private HotelAdmin convertToEntity(SaveHotelAdminResource resource){
        return mapper.map(resource, HotelAdmin.class);
    }

    private HotelAdminResource convertToResource(HotelAdmin entity){
        return mapper.map(entity, HotelAdminResource.class);
    }
}
