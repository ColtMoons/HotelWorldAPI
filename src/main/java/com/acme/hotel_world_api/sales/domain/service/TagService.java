package com.acme.hotel_world_api.sales.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acme.hotel_world_api.sales.domain.model.Tag;

public interface TagService {
    Page<Tag> getAllTags(Pageable pageable);
    Page<Tag> getAllTagsByProductId(Long productId, Pageable pageable);
    Tag getTagById(Long tagId);
    Tag createTag(Tag tag);
    Tag updateTag(Long tagId, Tag tagDetails);
    ResponseEntity<?> deleteTag(Long tagId);
}
