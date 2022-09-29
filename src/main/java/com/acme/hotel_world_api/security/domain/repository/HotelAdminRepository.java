package com.acme.hotel_world_api.security.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.hotel_world_api.security.domain.model.HotelAdmin;

@Repository
public interface HotelAdminRepository extends JpaRepository<HotelAdmin, Long> {
    Page<HotelAdmin> findByHotelId(Long hotelId, Pageable pageable);
    Optional<HotelAdmin> findByIdAndHotelId(Long id, Long hotelId);
}
