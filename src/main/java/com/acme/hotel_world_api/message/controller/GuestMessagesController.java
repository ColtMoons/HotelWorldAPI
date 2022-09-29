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

import com.acme.hotel_world_api.message.domain.model.MessageGuest;
import com.acme.hotel_world_api.message.domain.service.MessageGuestService;
import com.acme.hotel_world_api.message.resources.MessageGuestResource;
import com.acme.hotel_world_api.message.resources.SaveMessageGuestResource;

@RestController
@RequestMapping("/api")
public class GuestMessagesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MessageGuestService messageService;

    @GetMapping("/guests/{guestId}/messages")
    public Page<MessageGuestResource> getAllMessagesByGuestId(@PathVariable Long guestId, Pageable pageable){
        Page<MessageGuest> messagePage = messageService.getAllMessageByGuestId(guestId, pageable);
        List<MessageGuestResource> resources = messagePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    

    @GetMapping("/chats/{chatId}/messages")
    public Page<MessageGuestResource> getAllMessagesByChatId(@PathVariable Long chatId, Pageable pageable){
        Page<MessageGuest> messagePage = messageService.getAllMessageByChatId(chatId, pageable);
        List<MessageGuestResource> resources = messagePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/guests/{guestId}/messages/{messageId}")
    public MessageGuestResource getMessageByGuestId(@PathVariable Long guestId, @PathVariable Long messageId){
        return convertToResource(messageService.getMessageByIdAndGuestId(messageId, guestId));
    }


    @PostMapping("/guests/{guestId}/chats/{chatId}/messages")
    public MessageGuestResource createGuestMessage(@PathVariable Long guestId, @PathVariable Long chatId ,@Valid @RequestBody SaveMessageGuestResource resource){
        return convertToResource(messageService.createGuestMessage(guestId, chatId, convertToEntity(resource)));
    }

    @DeleteMapping("/guests/{guestId}/messages/{messageId}")
    public ResponseEntity<?> deleteGuestMessage(@PathVariable Long guestId, @PathVariable Long messageId){
        return messageService.deleteGuestMessage(guestId, messageId);
    }
    

    private MessageGuest convertToEntity(SaveMessageGuestResource resource){
        return mapper.map(resource, MessageGuest.class);
    }

    private MessageGuestResource convertToResource(MessageGuest entity){
        return mapper.map(entity, MessageGuestResource.class);
    }
}
