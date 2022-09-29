package com.acme.hotel_world_api.sales.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.hotel_world_api.sales.domain.model.Tag;
import com.acme.hotel_world_api.sales.domain.repository.ProductRepository;
import com.acme.hotel_world_api.sales.domain.repository.TagRepository;
import com.acme.hotel_world_api.sales.domain.service.TagService;
import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Page<Tag> getAllTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Page<Tag> getAllTagsByProductId(Long productId, Pageable pageable) {
        return productRepository.findById(productId).map(product ->{
            List<Tag> tags=product.getTags();
            int tagsCount= tags.size();
            return new PageImpl<>(tags, pageable,tagsCount);
        }).orElseThrow(()-> new ResourceNotFoundException("Product", "ID", productId));
    }

    @Override
    public Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", "ID", tagId));
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Long tagId, Tag tagDetails) {
        return tagRepository.findById(tagId).map(tag ->{
            tag.setName(tagDetails.getName());
            return tagRepository.save(tag);
        }).orElseThrow(() -> new ResourceNotFoundException("Tag", "ID", tagId));
    }

    @Override
    public ResponseEntity<?> deleteTag(Long tagId) {
        return tagRepository.findById(tagId).map(tag ->{
            tagRepository.delete(tag);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Tag", "ID", tagId));
    }
    
}
