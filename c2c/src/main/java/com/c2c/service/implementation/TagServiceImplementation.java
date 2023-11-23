package com.c2c.service.implementation;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.c2c.dto.TagDTO;
import com.c2c.model.Tag;
import com.c2c.repository.TagRepository;
import com.c2c.service.TagService;
import com.c2c.service.exception.TagNotFoundException;
import com.c2c.service.exception.TechnicalException;

@Service
public class TagServiceImplementation implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TagDTO> getAllTags() {
        List<Tag> listTagsEntity = tagRepository.findAll();
        List<TagDTO> listTagDTO = listTagsEntity.stream()
                .map(tag -> modelMapper.map(tag, TagDTO.class))
                .collect(Collectors.toList());
        return listTagDTO;
    }

    @Override
    public TagDTO getTagById(String idtag) {
        TagDTO tagDTO = null;
        try {
            Tag tagEntity = tagRepository.findByIdtag(idtag);
            tagDTO = modelMapper.map(tagEntity, TagDTO.class);
        } catch (EntityNotFoundException e) {
            throw new TagNotFoundException(idtag, e);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
        return tagDTO;
    }

    @Override
    public TagDTO createTag(TagDTO tag) {
        Tag tagEntity = modelMapper.map(tag, Tag.class);
        tagEntity = tagRepository.save(tagEntity);
        return modelMapper.map(tagEntity, TagDTO.class);
    }

    @Override
    public void deleteTag(String idtag) {
        try {
            tagRepository.deleteById(idtag);
        } catch (EmptyResultDataAccessException e) {
            throw new TagNotFoundException(idtag, e);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }
}
