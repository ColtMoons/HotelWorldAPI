package com.acme.hotel_world_api.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.hotel_world_api.sales.domain.model.Product;
import com.acme.hotel_world_api.sales.domain.repository.ProductRepository;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;
import com.acme.hotel_world_api.system.domain.model.Hotel;
import com.acme.hotel_world_api.system.domain.repository.HotelRepository;
import com.acme.hotel_world_api.system.domain.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Hotel> getAllHotel(Pageable pageable) {
        return hotelRepository.findAll(pageable);
    }

    @Override
    public Hotel getHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel", "ID", hotelId));
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(Long hotelId, Hotel hotelRequest) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel", "ID", hotelId));
        return hotelRepository.save(
            hotel.setName(hotelRequest.getName())
            .setAddress(hotelRequest.getAddress())
            .setPhone(hotelRequest.getPhone()));
    }

    @Override
    public ResponseEntity<?> deleteHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel", "ID", hotelId));

        hotelRepository.delete(hotel);
        
        return ResponseEntity.ok().build();
    }

    @Override
    public Hotel assingHotelProduct(Long hotelId, Long productId) {
        Product product=productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "ID", productId));

        return hotelRepository.findById(hotelId).map(hotel -> hotelRepository.save(hotel.addProduct(product))).orElseThrow(()-> new ResourceNotFoundException("Hotel", "ID", hotelId));
    }

    @Override
    public Hotel unassingHotelProduct(Long hotelId, Long productId) {
        Product product=productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "ID", productId));

        return hotelRepository.findById(hotelId).map(hotel -> hotelRepository.save(hotel.deleteProduct(product))).orElseThrow(()-> new ResourceNotFoundException("Hotel", "ID", hotelId));
    }
    
}
