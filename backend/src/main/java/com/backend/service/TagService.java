package com.backend.service;

import java.util.List;

import com.backend.dto.TagDTO;

public interface TagService {
    public List<TagDTO> getAllTags();

    public TagDTO getTagById(String idtag);

    public TagDTO createTag(TagDTO newTag);

    public void deleteTag(String idtag);
}
