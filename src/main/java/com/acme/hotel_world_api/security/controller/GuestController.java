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

import com.acme.hotel_world_api.security.domain.model.Guest;
import com.acme.hotel_world_api.security.domain.service.GuestService;
import com.acme.hotel_world_api.security.resource.GuestResource;
import com.acme.hotel_world_api.security.resource.SaveGuestResource;

@RestController
@RequestMapping("/api")
public class GuestController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private GuestService guestService;

    @GetMapping("/guests")
    public Page<GuestResource> getAllGuest(Pageable pageable){
        Page<Guest> guests = guestService.getAllGuest(pageable);

        List<GuestResource> resources = guests.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/guests/{guestId}")
    public GuestResource getGuestById(@PathVariable Long guestId){
        return convertToResource(guestService.getGuestById(guestId));
    }

    @PostMapping("/guests")
    public GuestResource createGuest(@Valid @RequestBody SaveGuestResource resource){
        return convertToResource(guestService.createGuest(convertToEntity(resource)));
    }

    @PutMapping("/guests/{guestId}")
    public GuestResource updateGuest(@PathVariable Long guestId, @Valid @RequestBody SaveGuestResource resource){
        return convertToResource(guestService.updateGuest(guestId, convertToEntity(resource)));
    }

    @DeleteMapping("/guests/{guestId}")
    public ResponseEntity<?> deleteGuest(@PathVariable Long guestId){
        return guestService.deleteGuest(guestId);
    }

    private Guest convertToEntity(SaveGuestResource resource){
        return mapper.map(resource, Guest.class);
    }

    private GuestResource convertToResource(Guest entity){
        return mapper.map(entity, GuestResource.class);
    }
}
