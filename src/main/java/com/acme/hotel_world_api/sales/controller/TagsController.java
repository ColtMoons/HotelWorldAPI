package com.acme.hotel_world_api.sales.controller;

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

import com.acme.hotel_world_api.sales.domain.model.Tag;
import com.acme.hotel_world_api.sales.domain.service.TagService;
import com.acme.hotel_world_api.sales.resource.SaveTagResource;
import com.acme.hotel_world_api.sales.resource.TagResource;

@RestController
@RequestMapping("/api")
public class TagsController{

    @Autowired
    private TagService tagService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/tags")
    public Page<TagResource> getAllTags(Pageable pageable){
        List<TagResource> tags = tagService.getAllTags(pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        int tagsCount= tags.size();
        return new PageImpl<>(tags, pageable, tagsCount);
    }

    @GetMapping("/tags/{id}")
    public TagResource getTagById(@PathVariable(name = "id") Long tagId){
        return convertToResource(tagService.getTagById(tagId));
    }

    @PostMapping("/tags")
    public TagResource createTag(@Valid @RequestBody SaveTagResource resource){
        return convertToResource(tagService.createTag(convertToEntity(resource)));
    }

    @GetMapping("/products/{productId}/tags")
    public Page<TagResource> getAllTagsByProductId(@PathVariable Long productId, Pageable pageable){
        List<TagResource> tags=tagService.getAllTagsByProductId(productId, pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int tagsCount=tags.size();
        return new PageImpl<>(tags,pageable,tagsCount);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "id") Long tagId){
        return tagService.deleteTag(tagId);
    }

    private Tag convertToEntity(SaveTagResource resource){
        return mapper.map(resource, Tag.class);
    }

    private TagResource convertToResource(Tag entity){
        return mapper.map(entity, TagResource.class);
    }
}