package com.acme.hotel_world_api.security.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.hotel_world_api.security.domain.model.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    
}
