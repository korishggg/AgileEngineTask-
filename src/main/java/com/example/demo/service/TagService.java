package com.example.demo.service;


import com.example.demo.entity.Author;
import com.example.demo.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> saveIfNotExist(List<Tag> tags);
}
