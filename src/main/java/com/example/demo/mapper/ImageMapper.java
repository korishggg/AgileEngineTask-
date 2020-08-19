package com.example.demo.mapper;

import com.example.demo.dto.ImageDTO;
import com.example.demo.entity.Image;

import java.util.stream.Collectors;

public class ImageMapper {
    public static ImageDTO mapToImageDTO(Image image){
        return new ImageDTO(image.getId(),
                image.getAuthor().getFullName(),
                image.getCamera().getCameraName(),
                image.getTags().stream().map(tag -> TagMapper.mapToTagDTO(tag)).collect(Collectors.toList()),
                image.getCropped_picture(),
                image.getFull_picture()
        );
    }
}
