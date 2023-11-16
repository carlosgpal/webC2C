package com.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c2c.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    Tag findByIdtag(String idtag);
}