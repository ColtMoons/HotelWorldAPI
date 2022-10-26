package com.acme.hotel_world_api.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.hotel_world_api.message.domain.model.Chat;
import com.acme.hotel_world_api.message.domain.repository.ChatRepository;
import com.acme.hotel_world_api.message.domain.service.ChatService;
import com.acme.hotel_world_api.security.domain.model.HotelAdmin;
import com.acme.hotel_world_api.security.domain.repository.GuestRepository;
import com.acme.hotel_world_api.security.domain.repository.HotelAdminRepository;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private HotelAdminRepository adminRepository;

    @Autowired
    private GuestRepository guestRepository;
    
    @Override
    public Chat getChatById(Long chatId) {
        return chatRepository.findById(chatId).orElseThrow(()-> new ResourceNotFoundException("Chat", "ID", chatId));
    }

    @Override
    public Page<Chat> getAllChatsByGuestId(Long guestId, Pageable pageable) {
        return chatRepository.findByGuestId(guestId, pageable);
    }

    @Override
    public Page<Chat> getAllChatsByAdminId(Long adminId, Pageable pageable) {
        return chatRepository.findByAdminId(adminId, pageable);
    }

    @Override
    public Chat getChatByIdAndGuestId(Long chatId, Long guestId) {
        return chatRepository.findByIdAndGuestId(chatId, guestId).orElseThrow(()-> new ResourceNotFoundException("Chat", " ID", chatId));
    }

    @Override
    public Chat getChatByIdAndAdminId(Long chatId, Long adminId) {
        return chatRepository.findByIdAndAdminId(chatId, adminId).orElseThrow(()-> new ResourceNotFoundException("Chat", " ID", chatId)); 
    }

    @Override
    public Chat createChat(Long guestId, Long adminId, Chat chat) {
        HotelAdmin admin = adminRepository.findById(adminId).orElseThrow(()->new ResourceNotFoundException("admin", " ID", adminId));

        return guestRepository.findById(guestId).map(guest->{
            chat.setGuest(guest).setAdmin(admin);
            return chatRepository.save(chat);
        }).orElseThrow(()-> new ResourceNotFoundException("Guest", " ID", guestId)); 
    }

    @Override
    public ResponseEntity<?> deleteChat(Long guestId, Long chatId) {
        if(!guestRepository.existsById(guestId)){
            throw new ResourceNotFoundException("Guest","Id", guestId);
        }

        return chatRepository.findById(chatId).map(chat->{
            chatRepository.delete(chat);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Chat", " ID", chatId)); 
    }
    
}
