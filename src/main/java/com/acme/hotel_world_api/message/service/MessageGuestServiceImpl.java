package com.acme.hotel_world_api.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.hotel_world_api.message.domain.model.Chat;
import com.acme.hotel_world_api.message.domain.model.MessageGuest;
import com.acme.hotel_world_api.message.domain.repository.ChatRepository;
import com.acme.hotel_world_api.message.domain.repository.MessageGuestRepository;
import com.acme.hotel_world_api.message.domain.service.MessageGuestService;
import com.acme.hotel_world_api.security.domain.repository.GuestRepository;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;

@Service
public class MessageGuestServiceImpl implements MessageGuestService{

    @Autowired
    private MessageGuestRepository messageRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ChatRepository chatRepository;


    @Override
    public Page<MessageGuest> getAllMessageByGuestId(Long guestId, Pageable pageable) {
        return messageRepository.findByGuestId(guestId, pageable);
    }

    @Override
    public MessageGuest getMessageByIdAndGuestId(Long messageId, Long guestId) {
        return messageRepository.findByIdAndGuestId(messageId,guestId).orElseThrow(()->new ResourceNotFoundException("Message with id"+ messageId+" guest id "+guestId));
    }

    @Override
    public MessageGuest createGuestMessage(Long guestId, Long chatId, MessageGuest message) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(()-> new ResourceNotFoundException("Chat", "id", chatId));
        return guestRepository.findById(guestId).map(guest->{
            message.setChat(chat).setGuest(guest);
            return messageRepository.save(message);
        }).orElseThrow(()-> new ResourceNotFoundException("Guest", "id", guestId));
    }

    @Override
    public ResponseEntity<?> deleteGuestMessage(Long guestId, Long messageId) {
        if(!guestRepository.existsById(guestId)){
            throw new ResourceNotFoundException("Guest","Id", guestId);
        }

        return messageRepository.findById(messageId).map(message->{
            messageRepository.delete(message);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("message", "id", messageId));
    }

    @Override
    public Page<MessageGuest> getAllMessageByChatId(Long chatId, Pageable pageable) {
        return messageRepository.findByChatId(chatId, pageable);
    }
    
}
