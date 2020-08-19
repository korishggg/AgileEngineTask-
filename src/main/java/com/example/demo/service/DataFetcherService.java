package com.example.demo.service;

import com.example.demo.api.client.response.ImageDTOResponse;

public interface DataFetcherService{
     ImageDTOResponse fetchImagesByPage(String page);
}
