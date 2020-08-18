package com.example.demo.service.impl;

import com.example.demo.entity.Author;
import com.example.demo.entity.Tag;
import com.example.demo.repository.TagRepository;
import com.example.demo.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> saveIfNotExist(List<Tag> tags) {
        List<Tag> list = tagRepository.findAll();
        List<Tag> toSave = tags.stream().filter(tag -> !list.contains(tag)).collect(Collectors.toList());

        tagRepository.saveAll(toSave);

        return toSave;
    }
}
