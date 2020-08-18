package com.example.demo.service;

import com.example.demo.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> saveIfNotExist(List<Author> authors);
}
