package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    List<Image> findAll();

    List<Image> findBySearchTerm(String searchTerm);

    Optional<Image> findImageById(String imageId);

    List<Image> saveIfNotExist(List<Image> images);
}
