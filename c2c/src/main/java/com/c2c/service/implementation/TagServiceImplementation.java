package com.c2c.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.c2c.model.Tag;
import com.c2c.repository.TagRepository;
import com.c2c.service.TagService;

public class TagServiceImplementation implements TagService{

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(String idtag) {
        return tagRepository.findById(idtag).orElse(null);
    }

    @Override
    public Tag createTag(Tag newTag) {
        return tagRepository.save(newTag);
    }

    @Override
    public Tag updateTag(String idtag, Tag newTag) {
        return tagRepository.findById(idtag)
                .map(tag -> {
                    tag.setName(newTag.getName());
                    tag.setProducts(newTag.getProducts());
                    return tagRepository.save(tag);
                })
                .orElseGet(() -> {
                    newTag.setIdtag(idtag);
                    return tagRepository.save(newTag);
                });
    }

    @Override
    public Tag deleteTag(String idtag) {
        Tag tag = tagRepository.findById(idtag).orElse(null);
        tagRepository.deleteById(idtag);
        return tag;
    }
    
}
