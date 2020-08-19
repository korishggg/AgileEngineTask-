package com.example.demo.mapper;

import com.example.demo.dto.TagDTO;
import com.example.demo.entity.Tag;

public class TagMapper {
    public static TagDTO mapToTagDTO(Tag tag){
        return new TagDTO(tag.getTagName());
    }
}
