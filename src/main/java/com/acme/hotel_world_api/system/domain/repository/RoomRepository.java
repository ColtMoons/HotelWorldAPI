package com.acme.hotel_world_api.system.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.hotel_world_api.system.domain.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
    Page<Room> findByHotelId(Long hotelId, Pageable pageable);
    Optional<Room> findByIdAndHotelId(Long id, Long hotelId);
}
