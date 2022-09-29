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

import com.acme.hotel_world_api.system.domain.model.Room;
import com.acme.hotel_world_api.system.domain.service.RoomService;
import com.acme.hotel_world_api.system.resource.RoomResource;
import com.acme.hotel_world_api.system.resource.SaveRoomResource;

@RestController
@RequestMapping("/api")
public class RoomsController {
    
    @Autowired
    private RoomService roomService;

    @Autowired
    private ModelMapper mapper;

    //
    @GetMapping("/hotels/{hotelId}/rooms")
    public Page<RoomResource> getAllRoomsByHotelId(@PathVariable Long hotelId, Pageable pageable){
        Page<Room> roomPage = roomService.getAllRoomsByHotelId(hotelId, pageable);
        List<RoomResource> resources = roomPage.getContent().stream().map(
            this::convertToResource
        ).collect(Collectors.toList());
        
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/hotels/{hotelId}/rooms/{roomId}")
    public RoomResource getRoomByIdAndHotelId(@PathVariable Long hotelId, @PathVariable Long roomId){
        return convertToResource(roomService.getRoomByIdAndHotelId(hotelId, roomId));
    }

    @PostMapping("/hotels/{hotelId}/rooms")
    public RoomResource createRoom(@PathVariable Long hotelId, @Valid @RequestBody SaveRoomResource resource){
        return convertToResource(roomService.createRoom(hotelId, convertToEntity(resource)));
    }

    @PutMapping("/hotels/{hotelId}/rooms/{roomId}")
    public RoomResource updateRoom(@PathVariable Long hotelId, @PathVariable Long roomId, @Valid @RequestBody SaveRoomResource resource){
        return convertToResource(roomService.updateRoom(hotelId, roomId, convertToEntity(resource)));
    }

    @DeleteMapping("/hotels/{hotelId}/rooms/{roomId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long hotelId, @PathVariable Long roomId){
        return roomService.deleteRoom(hotelId, roomId);
    }
    //


    private Room convertToEntity(SaveRoomResource resource){
        return mapper.map(resource, Room.class);
    }

    private RoomResource convertToResource(Room entity){
        return mapper.map(entity, RoomResource.class);
    }
}
