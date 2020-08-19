package com.example.demo.api.client.response;

import com.example.demo.dto.ImageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTOResponse {
    private Integer pages;
    private List<ImageDTO> imageDTOS;
}
