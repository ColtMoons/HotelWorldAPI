package com.acme.hotel_world_api.system.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acme.hotel_world_api.system.domain.model.Hotel;

public interface HotelService {
    Page<Hotel> getAllHotel(Pageable pageable);
    Hotel getHotelById(Long hotelId);
    Hotel createHotel(Hotel hotel);
    Hotel updateHotel(Long hotelId, Hotel hotelRequest);
    ResponseEntity<?> deleteHotel(Long hotelId);

    Hotel assingHotelProduct(Long hotelId, Long productId);
    Hotel unassingHotelProduct(Long hotelId, Long productId);
}
