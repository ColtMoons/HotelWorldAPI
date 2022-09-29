package com.acme.hotel_world_api.message.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.hotel_world_api.message.domain.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Page<Chat> findByGuestId(Long guestId, Pageable pageable);
    Page<Chat> findByAdminId(Long adminId, Pageable pageable);
    
    Optional<Chat> findByIdAndGuestId(Long id, Long guestId);
    Optional<Chat> findByIdAndAdminId(Long id, Long adminId);    
}
