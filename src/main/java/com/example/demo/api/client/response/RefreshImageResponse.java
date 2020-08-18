package com.example.demo.api.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RefreshImageResponse {
    private List<PictureResponse> pictures;
    private Integer page;
    private Integer pageCount;
    private boolean hasMore;
}
