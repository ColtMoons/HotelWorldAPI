package com.acme.hotel_world_api.message.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acme.hotel_world_api.message.domain.model.MessageGuest;

public interface MessageGuestService {
    Page<MessageGuest> getAllMessageByGuestId(Long guestId, Pageable pageable);
    Page<MessageGuest> getAllMessageByChatId(Long chatId, Pageable pageable);
    
    MessageGuest getMessageByIdAndGuestId(Long messageId, Long guestId);

    MessageGuest createGuestMessage(Long guestId,Long chatId, MessageGuest message);

    ResponseEntity<?> deleteGuestMessage(Long guestId, Long messageId);

}
