package com.example.demo.service;

import com.example.demo.dto.ImageDTO;
import com.example.demo.entity.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    List<ImageDTO> findAll();

    Optional<Image> findImageById(String imageId);

    List<Image> saveAll(List<Image> images);

}
