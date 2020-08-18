package com.example.demo.api.client.response;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.CameraDTO;
import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.TagDTO;
import com.example.demo.entity.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.text.resources.CollationData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ImageResponse {
    private String id;
    private String author;
    private String camera;
    private String tags;
    private String cropped_picture;
    private String full_picture;

    public ImageDTO mapToImageDTO() {
        String[] dtoTags = tags.split("[#]", 0);
        List<TagDTO> tagDTOS = (List<TagDTO>) new ArrayList(Arrays.asList(dtoTags)).stream()
                .map(el -> new TagDTO((String) el))
                .collect(Collectors.toList());
        return new ImageDTO(id, new AuthorDTO(author), new CameraDTO(camera),tagDTOS , cropped_picture, full_picture);
    }

}
