package com.acme.hotel_world_api.sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.hotel_world_api.sales.domain.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    
}
