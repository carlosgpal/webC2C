package com.backend.service;

import java.util.List;

import com.backend.dto.TagDTO;

// This is the port from the TagRepository to the TagController
public interface TagService {
    public List<TagDTO> getAllTags();

    public TagDTO getTagById(String idtag);

    public TagDTO createTag(TagDTO newTag);

    public void deleteTag(String idtag);
}
