package com.c2c.service.implementation;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.c2c.model.Tag;
import com.c2c.repository.TagRepository;
import com.c2c.service.TagService;

@Service
public class TagServiceImplementation implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        if (tags.isEmpty()) {
            throw new EntityNotFoundException("No tags found");
        }
        return tags;
    }

    @Override
    public Tag getTagById(String idtag) {
        return tagRepository.findById(idtag)
                .orElseThrow(() -> new EntityNotFoundException("Tag with ID: " + idtag + " not found"));
    }

    @Override
    public Tag createTag(Tag newTag) {
        validateTag(newTag);

        return tagRepository.save(newTag);
    }

    @Override
    public Tag createOrUpdateTag(String idtag, Tag newTag) {
        if (tagRepository.existsById(idtag)) {
            return updateTag(idtag, newTag);
        } else {
            return createTag(newTag);
        }
    }

    @Override
    public Tag updateTag(String idtag, Tag newTag) {
        validateTag(newTag);

        tagRepository.findById(idtag)
                .orElseThrow(() -> new EntityNotFoundException("Tag with ID: " + idtag + " not found"));

        newTag.setIdtag(idtag);
        return tagRepository.save(newTag);
    }

    @Override
    @Transactional
    public Tag deleteTag(String idtag) {
        Tag tag = tagRepository.findById(idtag)
                .orElseThrow(() -> new EntityNotFoundException("Tag with ID: " + idtag + " not found"));

        tag.getProducts().clear();
        tagRepository.save(tag);
        tagRepository.delete(tag);

        return tag;
    }

    private void validateTag(Tag tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Tag cannot be null");
        }
        if (tag.getIdtag() == null || tag.getIdtag().trim().isEmpty()) {
            throw new IllegalArgumentException("Tag id cannot be null or empty");
        }
        if (tag.getName() == null || tag.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tag name cannot be null or empty");
        }
    }
}
