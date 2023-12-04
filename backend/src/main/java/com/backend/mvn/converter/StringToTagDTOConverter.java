package com.backend.mvn.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.backend.dto.TagDTO;
import com.backend.service.TagService;

@Component
public class StringToTagDTOConverter implements Converter<String, TagDTO> {
    @Autowired
    TagService tagService;

    @Override
    public TagDTO convert(String id) {
        TagDTO tagDTO = tagService.getTagById(id);
        return tagDTO;
    }
}
