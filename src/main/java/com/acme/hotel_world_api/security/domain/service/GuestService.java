package com.acme.hotel_world_api.security.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acme.hotel_world_api.security.domain.model.Guest;

public interface GuestService {
    Page<Guest> getAllGuest(Pageable pageable);
    Guest getGuestById(Long guestId);
    Guest createGuest(Guest guest);
    Guest updateGuest(Long guestId, Guest guestRequest);
    ResponseEntity<?> deleteGuest(Long guestId);
}
