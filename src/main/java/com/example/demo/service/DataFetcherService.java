package com.example.demo.service;

import com.example.demo.dto.ImageDTO;

import java.util.List;

public interface DataFetcherService{
     List<ImageDTO> fetchImagesByPage(String page);
}
