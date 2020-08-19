package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private String id;
    private String author;
    private String camera;
    private List<TagDTO> tags;
    private String cropped_picture;
    private String full_picture;
}
