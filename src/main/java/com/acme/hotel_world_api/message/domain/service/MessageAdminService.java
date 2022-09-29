package com.acme.hotel_world_api.message.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acme.hotel_world_api.message.domain.model.MessageAdmin;

public interface MessageAdminService {
    Page<MessageAdmin> getAllMessageByAdminId(Long adminId, Pageable pageable);
    Page<MessageAdmin> getAllMessageByChatId(Long chatId, Pageable pageable);
    
    MessageAdmin getMessageByIdAndAdminId(Long messageId, Long adminId);

    MessageAdmin createAdminMessage(Long adminId,Long chatId, MessageAdmin message);

    ResponseEntity<?> deleteAdminMessage(Long adminId, Long messageId);
}
