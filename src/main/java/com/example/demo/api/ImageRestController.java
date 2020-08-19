package com.example.demo.api;

import com.example.demo.dto.ImageDTO;
import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImageRestController {

    private ImageService imageService;

    public ImageRestController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/images")
    public ResponseEntity<?> getImages(){
        List<ImageDTO> list = imageService.findAll();
        return list.isEmpty() ? new ResponseEntity(HttpStatus.NO_CONTENT) : new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/images/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable String imageId){
        return imageService.findImageById(imageId)
                .map(image -> new ResponseEntity(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(HttpStatus.NO_CONTENT));
    }
}
