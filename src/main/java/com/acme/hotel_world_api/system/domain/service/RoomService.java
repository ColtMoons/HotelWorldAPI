package com.acme.hotel_world_api.system.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acme.hotel_world_api.system.domain.model.Room;

public interface RoomService {
    Page<Room> getAllRoomsByHotelId(Long hotelId, Pageable pageable);
    Room getRoomByIdAndHotelId(Long hotelId, Long roomId);
    Room createRoom(Long hotelId, Room room);
    Room updateRoom(Long hotelId, Long roomId, Room roomDetails);
    ResponseEntity<?> deleteRoom(Long hotelId, Long roomId);
}
