package com.acme.hotel_world_api.message.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acme.hotel_world_api.message.domain.model.Chat;

public interface ChatService {
    Chat getChatById(Long chatId);
    Page<Chat> getAllChatsByGuestId(Long guestId, Pageable pageable);
    Page<Chat> getAllChatsByAdminId(Long adminId, Pageable pageable);
    
    Chat getChatByIdAndGuestId(Long chatId, Long guestId);
    Chat getChatByIdAndAdminId(Long chatId, Long adminId);

    Chat createChat(Long guestId, Long adminId, Chat chat);
    ResponseEntity<?> deleteChat(Long guestId, Long chatId);
}
