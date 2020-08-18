package com.example.demo.service.impl;

import com.example.demo.entity.Author;
import com.example.demo.entity.Camera;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> saveIfNotExist(List<Author> authors) {
        List<Author> list = authorRepository.findAll();
        List<Author> toSave =  authors.stream().filter(author -> !list.contains(author)).collect(Collectors.toList());

        authorRepository.saveAll(toSave);

        return toSave;

    }
}
