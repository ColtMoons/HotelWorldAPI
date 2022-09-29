package com.acme.hotel_world_api.message.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.hotel_world_api.message.domain.model.Chat;
import com.acme.hotel_world_api.message.domain.service.ChatService;
import com.acme.hotel_world_api.message.resources.ChatResource;
import com.acme.hotel_world_api.message.resources.SaveChatResource;

@RestController
@RequestMapping("/api")
public class ChatsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ChatService chatService;

    @GetMapping("/guests/{guestId}/chats")
    public Page<ChatResource> getAllChatsByGuestId(@PathVariable Long guestId, Pageable pageable){
        Page<Chat> chatsPage=chatService.getAllChatsByGuestId(guestId, pageable);
        List<ChatResource> resources=chatsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/admins/{adminId}/chats")
    public Page<ChatResource> getAllChatsByAdminId(@PathVariable Long adminId, Pageable pageable){
        Page<Chat> chatsPage=chatService.getAllChatsByAdminId(adminId, pageable);
        List<ChatResource> resources=chatsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
 
    @GetMapping("/guests/{guestId}/chats/{chatId}")
    public ChatResource getChatByGuestId(@PathVariable Long guestId, @PathVariable Long chatId){
        return convertToResource(chatService.getChatByIdAndGuestId(chatId, guestId));
    }

    @GetMapping("/admins/{adminId}/chats/{chatId}")
    public ChatResource getChatByAdminId(@PathVariable Long adminId, @PathVariable Long chatId){
        return convertToResource(chatService.getChatByIdAndAdminId(chatId, adminId));
    }

    @PostMapping("/guests/{guestId}/admins/{adminId}/chats")
    public ChatResource createChat(@PathVariable Long guestId, @PathVariable Long adminId, @Valid @RequestBody SaveChatResource resource){
        return convertToResource(chatService.createChat(guestId, adminId, convertToEntity(resource)));
    }

    @DeleteMapping("/guests/{guestId}/chats/{chatId}")
    public ResponseEntity<?> deleteChat(@PathVariable Long guestId, @PathVariable Long chatId){
        return chatService.deleteChat(guestId, chatId);
    }

    


    private Chat convertToEntity(SaveChatResource resource){
        return mapper.map(resource, Chat.class);
    }

    private ChatResource convertToResource(Chat entity){
        return mapper.map(entity, ChatResource.class);
    }
}
