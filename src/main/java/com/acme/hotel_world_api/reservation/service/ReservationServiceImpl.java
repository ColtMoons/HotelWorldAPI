package com.acme.hotel_world_api.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.hotel_world_api.reservation.domain.model.Reservation;
import com.acme.hotel_world_api.reservation.domain.repository.ReservationRepository;
import com.acme.hotel_world_api.reservation.domain.service.ReservationService;
import com.acme.hotel_world_api.security.domain.repository.GuestRepository;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;
import com.acme.hotel_world_api.system.domain.model.Room;
import com.acme.hotel_world_api.system.domain.repository.RoomRepository;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;


    @Override
    public Page<Reservation> getAllReservationByGuestId(Long guestId, Pageable pageable) {
        return reservationRepository.findByGuestId(guestId, pageable);
    }

    @Override
    public Page<Reservation> getAllReservationByRoomId(Long roomId, Pageable pageable) {
        return reservationRepository.findByRoomId(roomId, pageable);
    }

    @Override
    public Reservation getReservationByIdAndGuestId(Long reservationId, Long guestId) {
        return reservationRepository.findByIdAndGuestId(reservationId, guestId).orElseThrow(()->new ResourceNotFoundException("Reservation not found with id " + reservationId+" and Guest Id " + guestId));
    }

    @Override
    public Reservation getReservationByIdAndRoomId(Long reservationId, Long roomId) {
        return reservationRepository.findByIdAndRoomId(reservationId, roomId).orElseThrow(()->new ResourceNotFoundException("Reservation not found with id " + reservationId+" and Room Id " + roomId));
    }

    @Override
    public Reservation createReservation(Long guestId, Long roomId, Reservation reservation) {
        Room room = roomRepository.findById(roomId).orElseThrow(()->new ResourceNotFoundException("Room","Id", roomId));

        return guestRepository.findById(guestId).map(guest->{
            reservation.setGuest(guest).setRoom(room);
            return reservationRepository.save(reservation);
        }).orElseThrow(()->new ResourceNotFoundException("Guest","Id", guestId));
    }

    @Override
    public Reservation updateReservation(Long guestId, Long reservationId, Reservation reservationDetails) {
        if(!guestRepository.existsById(guestId)){
            throw new ResourceNotFoundException("Guest","Id", guestId);
        }

        return reservationRepository.findById(reservationId).map(reservation ->{
            reservation.setDate(reservationDetails.getDate()).setRoom(reservationDetails.getRoom());
            return reservationRepository.save(reservation);
        }).orElseThrow(()->new ResourceNotFoundException("Reservation ", " Id ", reservationId));
    }

    @Override
    public ResponseEntity<?> deleteReservation(Long guestId, Long reservationId) {
        if(!guestRepository.existsById(guestId)){
            throw new ResourceNotFoundException("Guest","Id", guestId);
        }

        return reservationRepository.findById(reservationId).map(reservation ->{
            reservationRepository.delete(reservation);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Reservation ", " Id ", reservationId));
    }
    
}
