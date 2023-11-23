package com.c2c.service;

import java.util.List;

import com.c2c.dto.TagDTO;

public interface TagService {
    public List<TagDTO> getAllTags();

    public TagDTO getTagById(String idtag);

    public TagDTO createTag(TagDTO newTag);

    public void deleteTag(String idtag);
}
