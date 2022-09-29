package com.acme.hotel_world_api.message.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.hotel_world_api.message.domain.model.MessageGuest;

@Repository
public interface MessageGuestRepository extends JpaRepository<MessageGuest, Long>{
    Page<MessageGuest> findByGuestId(Long guestId, Pageable pageable);
    Page<MessageGuest> findByChatId(Long chatId, Pageable pageable);

    Optional<MessageGuest> findByIdAndGuestId(Long id, Long guestId);
    Optional<MessageGuest> findByIdAndChatId(Long id, Long chatId);
}
