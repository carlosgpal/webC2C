package com.c2c.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.c2c.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    Tag findByIdtag(String idtag);

    @Query("select t from Tag t where t.idtag in :ids")
    List<Tag> findByTags(@Param("ids") List<String> ids);
}