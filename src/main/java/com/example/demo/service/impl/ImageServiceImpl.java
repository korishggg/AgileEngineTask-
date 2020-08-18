package com.example.demo.service.impl;

import com.example.demo.entity.Author;
import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public List<Image> findBySearchTerm(String searchTerm) {
//        TODO
        return new ArrayList<>();
    }

    @Override
    public Optional<Image> findImageById(String imageId) {
        return imageRepository.findImageById(imageId);
    }

    @Override
    public List<Image> saveIfNotExist(List<Image> images) {
        List<Image>list = imageRepository.findAll();
        List<Image> toSave = images.stream().filter(image -> !list.contains(image)).collect(Collectors.toList());

        imageRepository.saveAll(toSave);

        return toSave;
    }
}
