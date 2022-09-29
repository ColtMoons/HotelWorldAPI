package com.acme.hotel_world_api.reservation.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.hotel_world_api.reservation.domain.model.Reservation;
import com.acme.hotel_world_api.reservation.domain.service.ReservationService;
import com.acme.hotel_world_api.reservation.resources.ReservationResource;
import com.acme.hotel_world_api.reservation.resources.SaveReservationResource;

@RestController
@RequestMapping("/api")
public class ReservationsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/guests/{guestId}/reservations")
    public Page<ReservationResource> getAllReservationsByGuestId(@PathVariable Long guestId, Pageable pageable){
        Page<Reservation> reservationPage=reservationService.getAllReservationByGuestId(guestId, pageable);
        List<ReservationResource> resources = reservationPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/rooms/{roomId}/reservations")
    public Page<ReservationResource> getAllReservationsByRoomId(@PathVariable Long roomId, Pageable pageable){
        Page<Reservation> reservationPage=reservationService.getAllReservationByRoomId(roomId, pageable);
        List<ReservationResource> resources = reservationPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/guests/{guestId}/reservations/{reservationId}")
    public ReservationResource getReservationByIdAndGuestId(@PathVariable Long guestId, @PathVariable Long reservationId){
        return convertToResource(reservationService.getReservationByIdAndGuestId(reservationId, guestId));
    }

    @GetMapping("/rooms/{roomId}/reservations/{reservationId}")
    public ReservationResource getReservationByIdAndRoomId(@PathVariable Long roomId, @PathVariable Long reservationId){
        return convertToResource(reservationService.getReservationByIdAndRoomId(reservationId, roomId));
    }

    @PostMapping("/guests/{guestId}/rooms/{roomId}/reservation")
    public ReservationResource createReservation(@PathVariable Long guestId, @PathVariable Long roomId, @Valid @RequestBody SaveReservationResource resource){
        return convertToResource(reservationService.createReservation(guestId, roomId, convertToEntity(resource)));
    }

    @PutMapping("/guests/{guestId}/reservations/{reservationId}")
    public ReservationResource updateReservation(@PathVariable Long guestId, @PathVariable Long reservationId, @Valid @RequestBody SaveReservationResource resource){
        return convertToResource(reservationService.updateReservation(guestId, reservationId, convertToEntity(resource)));
    }

    @DeleteMapping("/guests/{guestId}/reservations/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long guestId, @PathVariable Long reservationId){
        return reservationService.deleteReservation(guestId, reservationId);
    }

    private Reservation convertToEntity(SaveReservationResource resource){
        return mapper.map(resource, Reservation.class);
    }

    private ReservationResource convertToResource(Reservation entity){
        return mapper.map(entity, ReservationResource.class);
    }
}
