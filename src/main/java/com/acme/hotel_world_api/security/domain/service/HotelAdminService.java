package com.acme.hotel_world_api.security.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acme.hotel_world_api.security.domain.model.HotelAdmin;

public interface HotelAdminService {
    Page<HotelAdmin> getAllHotelAdminByHotelId(Long hotelId, Pageable pageable);
    HotelAdmin getHotelAdminByIdAndHotelId(Long hotelId, Long hotelAdminId);
    HotelAdmin createHotelAdmin(Long hotelId, HotelAdmin hotelAdmin);
    HotelAdmin updateHotelAdmin(Long hotelId, Long hotelAdminId, HotelAdmin hotelAdminDetails);
    ResponseEntity<?> deleteHotelAdmin(Long hotelId, Long hotelAdminId);
}
