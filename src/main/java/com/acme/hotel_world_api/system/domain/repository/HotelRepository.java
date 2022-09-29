package com.acme.hotel_world_api.system.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.hotel_world_api.system.domain.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{
}
