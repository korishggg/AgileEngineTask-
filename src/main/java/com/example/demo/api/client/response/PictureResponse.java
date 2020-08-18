package com.example.demo.api.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PictureResponse {
    private String id;
    private String cropped_picture;
}
