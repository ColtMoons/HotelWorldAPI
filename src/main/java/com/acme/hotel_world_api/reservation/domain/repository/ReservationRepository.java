package com.acme.hotel_world_api.reservation.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.hotel_world_api.reservation.domain.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    Page<Reservation> findByGuestId(Long guestId, Pageable pageable);
    Page<Reservation> findByRoomId(Long roomId, Pageable pageable);
    Optional<Reservation> findByIdAndGuestId(Long id, Long guestId);
    Optional<Reservation> findByIdAndRoomId(Long id, Long roomId);
}

