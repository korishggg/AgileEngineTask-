package com.example.demo.service.impl;

import com.example.demo.api.client.response.*;
import com.example.demo.api.client.utils.TokenUtil;
import com.example.demo.entity.Author;
import com.example.demo.entity.Camera;
import com.example.demo.entity.Image;
import com.example.demo.entity.Tag;
import com.example.demo.mapper.ImageMapper;
import com.example.demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFetcherServiceImpl implements DataFetcherService {

    private static final Logger log = LoggerFactory.getLogger(DataFetcherServiceImpl.class);

    @Value("${agileengine.external.url}")
    private String externalUrl;

    private TokenUtil tokenUtil;
    private RestTemplate restTemplate;
    private ImageService imageService;

    public DataFetcherServiceImpl(TokenUtil tokenUtil, RestTemplate restTemplate, ImageService imageService) {
        this.tokenUtil = tokenUtil;
        this.restTemplate = restTemplate;
        this.imageService = imageService;
    }

    @Override
    public ImageDTOResponse fetchImagesByPage(String page) {

        HttpEntity<String> httpEntity = getCompletedHttpEntity();
        ResponseEntity<RefreshImageResponse> responseEntity;

        if (page == null || page.equals("")) {
            responseEntity = restTemplate.exchange(externalUrl+"/images", HttpMethod.GET, httpEntity, RefreshImageResponse.class);
        } else {
            responseEntity = restTemplate.exchange(externalUrl+"/images?page=" + page, HttpMethod.GET, httpEntity, RefreshImageResponse.class);
        }

        RefreshImageResponse refreshImageResponse = responseEntity.getBody();
        List<PictureResponse> pictureResponses = refreshImageResponse.getPictures();
        List<ImageResponse> imageResponses = new ArrayList<>();
        for (PictureResponse pictureResponse : pictureResponses) {
            ResponseEntity<ImageResponse> imageResponseResponseEntity = restTemplate.exchange(externalUrl+"/images/" + pictureResponse.getId(), HttpMethod.GET, httpEntity, ImageResponse.class);
            imageResponses.add(imageResponseResponseEntity.getBody());
        }

        List<Image> images = mapImageResponsesToImages(imageResponses);

        imageService.saveAll(images);

        log.info("Persisted fetched data for " + page + " page");

        return new ImageDTOResponse(refreshImageResponse.getPageCount(),
                images.stream()
                        .map(image -> ImageMapper.mapToImageDTO(image))
                        .collect(Collectors.toList())
        );
    }

    private HttpEntity<String> getCompletedHttpEntity() {
        AgileEngineAuthorizationResponse agileEngineAuthorizationResponse = tokenUtil.retrieveToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + agileEngineAuthorizationResponse.getToken());

        return new HttpEntity<String>(headers);
    }

    private List<Image> mapImageResponsesToImages(List<ImageResponse> imageResponses){
        List<Image> images = new ArrayList<>();
        for (ImageResponse imageResponse: imageResponses) {
            Image image = new Image(imageResponse.getId(), imageResponse.getCropped_picture(), imageResponse.getFull_picture());
            Camera camera = new Camera(imageResponse.getCamera() == null ? "" : imageResponse.getCamera());
            Author author = new Author(imageResponse.getAuthor() == null ? "" : imageResponse.getAuthor());
            List<Tag> tags = Arrays.asList(imageResponse.getTags().split("[#]", 0)).stream()
                    .filter(tag -> !tag.isEmpty())
                    .map(tag -> new Tag(tag))
                    .collect(Collectors.toList());

            image.setTags(tags);
            image.setCamera(camera);
            image.setAuthor(author);
            images.add(image);
        }
        return images;
    }
}
