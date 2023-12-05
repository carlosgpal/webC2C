package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.Tag;

// This is the repository for the Tag model of the MySQL database
@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    Tag findByIdtag(String idtag);

    // This is a custom query that returns a list of tags that match the ids passed
    @Query("select t from Tag t where t.idtag in :ids")
    List<Tag> findByTags(@Param("ids") List<String> ids);
}