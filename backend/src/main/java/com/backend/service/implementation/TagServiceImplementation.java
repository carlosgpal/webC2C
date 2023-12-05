package com.backend.service.implementation;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dto.TagDTO;
import com.backend.model.Tag;
import com.backend.repository.ProductRepository;
import com.backend.repository.TagRepository;
import com.backend.service.TagService;
import com.backend.service.exception.TagNotFoundException;
import com.backend.service.exception.TechnicalException;

// This is a service that implements the methods of the TagService interface
@Service
public class TagServiceImplementation implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get all tags
    @Override
    public List<TagDTO> getAllTags() {
        List<Tag> listTagsEntity = tagRepository.findAll();
        List<TagDTO> listTagDTO = listTagsEntity.stream()
                .map(tag -> modelMapper.map(tag, TagDTO.class))
                .collect(Collectors.toList());
        return listTagDTO;
    }

    // Get tag by ID
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

    // Create a new tag
    @Override
    public TagDTO createTag(TagDTO tag) {
        Tag tagEntity = modelMapper.map(tag, Tag.class);
        tagEntity = tagRepository.save(tagEntity);
        return modelMapper.map(tagEntity, TagDTO.class);
    }

    // Delete a tag, when the tag is deleted, the products associated with the tag
    // are not deleted
    @Override
    @Transactional
    public void deleteTag(String idtag) {
        try {
            Tag tagEntity = tagRepository.findByIdtag(idtag);

            // Remove the tag from associated products
            tagEntity.getProducts().forEach(product -> {
                product = productRepository.findByIdproduct(product.getIdproduct());
                product.getTags().remove(tagEntity);
                productRepository.save(product);
            });

            // Clear the products associated with the tag
            tagEntity.getProducts().clear();

            // Delete the tag
            tagRepository.delete(tagEntity);
        } catch (EmptyResultDataAccessException e) {
            throw new TagNotFoundException(idtag, e);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }
}
