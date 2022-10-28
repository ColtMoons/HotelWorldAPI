package com.acme.hotel_world_api.reservation.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acme.hotel_world_api.reservation.domain.model.Reservation;

public interface ReservationService {
    Reservation getReservationById(Long reservationId);
    Page<Reservation> getAllReservationByGuestId(Long guestId, Pageable pageable);
    Page<Reservation> getAllReservationByRoomId(Long roomId, Pageable pageable);
    
    Reservation getReservationByIdAndGuestId(Long reservationId, Long guestId);
    Reservation getReservationByIdAndRoomId(Long reservationId, Long roomId);

    Reservation createReservation(Long guestId, Long roomId, Reservation reservation);
    Reservation updateReservation(Long guestId, Long reservationId, Reservation reservationDetails);
    ResponseEntity<?> deleteReservation(Long guestId, Long reservationId);
}
