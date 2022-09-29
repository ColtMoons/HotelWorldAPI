package com.acme.hotel_world_api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.hotel_world_api.security.domain.model.HotelAdmin;
import com.acme.hotel_world_api.security.domain.repository.HotelAdminRepository;
import com.acme.hotel_world_api.security.domain.service.HotelAdminService;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;
import com.acme.hotel_world_api.system.domain.repository.HotelRepository;

@Service
public class HotelAdminServiceImpl implements HotelAdminService{

    @Autowired
    private HotelAdminRepository hotelAdminRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Page<HotelAdmin> getAllHotelAdminByHotelId(Long hotelId, Pageable pageable) {
        return hotelAdminRepository.findByHotelId(hotelId, pageable);
    }

    @Override
    public HotelAdmin getHotelAdminByIdAndHotelId(Long hotelId, Long hotelAdminId) {
        return hotelAdminRepository.findByIdAndHotelId(hotelAdminId, hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel Administrator not found with Id" + hotelAdminId + "and HotelId" + hotelId));
        
    }

    @Override
    public HotelAdmin createHotelAdmin(Long hotelId, HotelAdmin hotelAdmin) {
        return hotelRepository.findById(hotelId).map(hotel->{
            hotelAdmin.setHotel(hotel);
            return hotelAdminRepository.save(hotelAdmin);
        }).orElseThrow(()-> new ResourceNotFoundException("Hotel", "ID", hotelId));
    }

    @Override
    public HotelAdmin updateHotelAdmin(Long hotelId, Long hotelAdminId, HotelAdmin hotelAdminDetails) {
        if(!hotelRepository.existsById(hotelId)){
            throw new ResourceNotFoundException("Hotel", "Id", hotelId);
        }

        return hotelAdminRepository.findById(hotelAdminId).map(hotelAdmin->{
            hotelAdmin.setName(hotelAdminDetails.getName()).setUsername(hotelAdminDetails.getUsername()).setPassword(hotelAdminDetails.getPassword()).setPhone(hotelAdminDetails.getPhone());
            return hotelAdminRepository.save(hotelAdmin);
        }).orElseThrow(()->new ResourceNotFoundException("Hotel Administrator", "ID", hotelId));
    }

    @Override
    public ResponseEntity<?> deleteHotelAdmin(Long hotelId, Long hotelAdminId) {
        if(!hotelRepository.existsById(hotelId)){
            throw new ResourceNotFoundException("Hotel", "Id", hotelId);
        }

        return hotelAdminRepository.findById(hotelAdminId).map(hotelAdmin->{
            hotelAdminRepository.delete(hotelAdmin);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Hotel Administrator", "ID", hotelId));
    }
    
}
