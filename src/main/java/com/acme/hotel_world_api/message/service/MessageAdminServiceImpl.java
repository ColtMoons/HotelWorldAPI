package com.acme.hotel_world_api.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.hotel_world_api.message.domain.model.Chat;
import com.acme.hotel_world_api.message.domain.model.MessageAdmin;
import com.acme.hotel_world_api.message.domain.repository.ChatRepository;
import com.acme.hotel_world_api.message.domain.repository.MessageAdminRepository;
import com.acme.hotel_world_api.message.domain.service.MessageAdminService;
import com.acme.hotel_world_api.security.domain.repository.HotelAdminRepository;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;

@Service
public class MessageAdminServiceImpl implements MessageAdminService{

    @Autowired
    private MessageAdminRepository messageAdminRepository;

    @Autowired
    private HotelAdminRepository adminRepository;

    @Autowired
    private ChatRepository chatRepository;
    
    @Override
    public Page<MessageAdmin> getAllMessageByAdminId(Long adminId, Pageable pageable) {
        return messageAdminRepository.findByAdminId(adminId, pageable);
    }

    @Override
    public Page<MessageAdmin> getAllMessageByChatId(Long chatId, Pageable pageable) {
        return messageAdminRepository.findByChatId(chatId, pageable);
    }

    @Override
    public MessageAdmin getMessageByIdAndAdminId(Long messageId, Long adminId) {
        return messageAdminRepository.findByIdAndAdminId(messageId, adminId).orElseThrow(()->new ResourceNotFoundException("Message with id"+ messageId+" admin id "+adminId));
    }

    @Override
    public MessageAdmin createAdminMessage(Long adminId, Long chatId, MessageAdmin message) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(()-> new ResourceNotFoundException("Chat", "id", chatId));

        return adminRepository.findById(adminId).map(admin->{
            message.setChat(chat).setAdmin(admin);
            return messageAdminRepository.save(message);
        }).orElseThrow(()-> new ResourceNotFoundException("Admin", "id", adminId));
    }

    @Override
    public ResponseEntity<?> deleteAdminMessage(Long adminId, Long messageId) {
        if(!adminRepository.existsById(adminId)){
            throw new ResourceNotFoundException("Admin","Id", adminId);
        }

        return messageAdminRepository.findById(messageId).map(message->{
            messageAdminRepository.delete(message);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("message", "id", messageId));
    }
    
}
