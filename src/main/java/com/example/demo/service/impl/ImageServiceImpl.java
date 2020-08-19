package com.example.demo.service.impl;

import com.example.demo.dto.ImageDTO;
import com.example.demo.entity.Image;
import com.example.demo.mapper.ImageMapper;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import org.springframework.stereotype.Service;

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
    public List<ImageDTO> findAll() {
        List<Image> images = imageRepository.findAll();
        return images.stream().map(image -> ImageMapper.mapToImageDTO(image)).collect(Collectors.toList());
    }

    @Override
    public Optional<Image> findImageById(String imageId) {
        return imageRepository.findImageById(imageId);
    }

    @Override
    public List<Image> saveAll(List<Image> images) {
        return imageRepository.saveAll(images);
    }
}
