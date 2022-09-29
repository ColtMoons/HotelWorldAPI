package com.acme.hotel_world_api.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;
import com.acme.hotel_world_api.system.domain.model.Room;
import com.acme.hotel_world_api.system.domain.repository.HotelRepository;
import com.acme.hotel_world_api.system.domain.repository.RoomRepository;
import com.acme.hotel_world_api.system.domain.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Page<Room> getAllRoomsByHotelId(Long hotelId, Pageable pageable) {
        return roomRepository.findByHotelId(hotelId, pageable);
    }

    @Override
    public Room getRoomByIdAndHotelId(Long hotelId, Long roomId) {
        return roomRepository.findByIdAndHotelId(roomId, hotelId).orElseThrow(()->new ResourceNotFoundException("Room not found with Id" + roomId + "and HotelId" + hotelId));
    }

    @Override
    public Room createRoom(Long hotelId, Room room) {
        return hotelRepository.findById(hotelId).map(hotel -> {
            room.setHotel(hotel);
            return roomRepository.save(room);
        }).orElseThrow(()->new ResourceNotFoundException("Hotel", "ID", hotelId));

    }

    @Override
    public Room updateRoom(Long hotelId, Long roomId, Room roomDetails) {
        if(!hotelRepository.existsById(hotelId)){
            throw new ResourceNotFoundException("Hotel", "Id", hotelId);
        }
        return roomRepository.findById(roomId).map(room ->{
            room.setRoomNumber(roomDetails.getRoomNumber())
            .setRoomType(roomDetails.getRoomType())
            .setPrice(roomDetails.getPrice());
            return roomRepository.save(room);
        }).orElseThrow(()->new ResourceNotFoundException("Room", "ID", roomId));
    }

    @Override
    public ResponseEntity<?> deleteRoom(Long hotelId, Long roomId) {
        if(!hotelRepository.existsById(hotelId)){
            throw new ResourceNotFoundException("Hotel", "Id", hotelId);
        }
        return roomRepository.findById(roomId).map(room ->{
            roomRepository.delete(room);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Room", "ID", roomId));
    }
    
}
