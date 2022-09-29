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

import com.acme.hotel_world_api.message.domain.model.MessageAdmin;
import com.acme.hotel_world_api.message.domain.service.MessageAdminService;
import com.acme.hotel_world_api.message.resources.MessageAdminResource;
import com.acme.hotel_world_api.message.resources.SaveMessageAdminResource;

@RestController
@RequestMapping("/api")
public class AdminMessagesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MessageAdminService messageAdminService;

    @GetMapping("/admins/{adminId}/messages")
    public Page<MessageAdminResource> getAllMessagesByAdminId(@PathVariable Long adminId, Pageable pageable){
        Page<MessageAdmin> messagePage = messageAdminService.getAllMessageByAdminId(adminId, pageable);
        List<MessageAdminResource> resources = messagePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/chats/{chatId}/admin/messages")
    public Page<MessageAdminResource> getAllMessagesByChatId(@PathVariable Long chatId, Pageable pageable){
        Page<MessageAdmin> messagePage = messageAdminService.getAllMessageByChatId(chatId, pageable);
        List<MessageAdminResource> resources = messagePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/admins/{adminId}/messages/{messageId}")
    public MessageAdminResource getMessageByAdminId(@PathVariable Long adminId, @PathVariable Long messageId){
        return convertToResource(messageAdminService.getMessageByIdAndAdminId(messageId, adminId));
    }

    @PostMapping("/admins/{adminId}/chats/{chatId}/messages")
    public MessageAdminResource createAdminMessage(@PathVariable Long adminId, @PathVariable Long chatId ,@Valid @RequestBody SaveMessageAdminResource resource){
        return convertToResource(messageAdminService.createAdminMessage(adminId, chatId, convertToEntity(resource)));
    }
    
    @DeleteMapping("/admins/{adminId}/messages/{messageId}")
    public ResponseEntity<?> deleteAdminMessage(@PathVariable Long adminId, @PathVariable Long messageId){
        return messageAdminService.deleteAdminMessage(adminId, messageId);
    }    


    private MessageAdmin convertToEntity(SaveMessageAdminResource resource){
        return mapper.map(resource, MessageAdmin.class);
    }

    private MessageAdminResource convertToResource(MessageAdmin entity){
        return mapper.map(entity, MessageAdminResource.class);
    }
}
