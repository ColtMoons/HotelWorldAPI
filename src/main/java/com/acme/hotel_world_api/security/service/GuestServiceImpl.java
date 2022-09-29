package com.acme.hotel_world_api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.hotel_world_api.security.domain.model.Guest;
import com.acme.hotel_world_api.security.domain.repository.GuestRepository;
import com.acme.hotel_world_api.security.domain.service.GuestService;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public Page<Guest> getAllGuest(Pageable pageable) {
        return guestRepository.findAll(pageable);
    }

    @Override
    public Guest getGuestById(Long guestId) {
        return guestRepository.findById(guestId).orElseThrow(()->new ResourceNotFoundException("Guest", "ID", guestId));
    }

    @Override
    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Guest updateGuest(Long guestId, Guest guestRequest) {
        return guestRepository.findById(guestId).map(guest->{
            guest.setName(guestRequest.getName()).setPassword(guestRequest.getPassword()).setUsername(guestRequest.getUsername());
            return guestRepository.save(guest);
        }).orElseThrow(()->new ResourceNotFoundException("Guest", "ID", guestId));
    }

    @Override
    public ResponseEntity<?> deleteGuest(Long guestId) {
        Guest guest = guestRepository.findById(guestId).orElseThrow(()->new ResourceNotFoundException("Guest", "ID", guestId));

        guestRepository.delete(guest);
        
        return ResponseEntity.ok().build();
    }
    
}
