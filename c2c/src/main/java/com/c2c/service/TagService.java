package com.c2c.service;

import java.util.List;

import com.c2c.model.Tag;

public interface TagService {
    public List<Tag> getAllTags();

    public Tag getTagById(String idtag);

    public Tag createTag(Tag newTag);

    public Tag createOrUpdateTag(String idtag, Tag newTag);

    public Tag updateTag(String idtag, Tag newTag);

    public Tag deleteTag(String idtag);
}
