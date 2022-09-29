package com.acme.hotel_world_api.message.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.hotel_world_api.message.domain.model.MessageAdmin;

@Repository
public interface MessageAdminRepository extends JpaRepository<MessageAdmin, Long>{
    Page<MessageAdmin> findByAdminId(Long adminId, Pageable pageable);
    Page<MessageAdmin> findByChatId(Long chatId, Pageable pageable);

    Optional<MessageAdmin> findByIdAndAdminId(Long id, Long adminId);
    Optional<MessageAdmin> findByIdAndChatId(Long id, Long chatId);
}
