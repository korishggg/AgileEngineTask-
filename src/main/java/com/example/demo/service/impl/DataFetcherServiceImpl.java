package com.example.demo.service.impl;

import com.example.demo.api.client.response.AgileEngineAuthorizationResponse;
import com.example.demo.api.client.response.ImageResponse;
import com.example.demo.api.client.response.PictureResponse;
import com.example.demo.api.client.response.RefreshImageResponse;
import com.example.demo.api.client.utils.TokenUtil;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.CameraDTO;
import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.TagDTO;
import com.example.demo.entity.Author;
import com.example.demo.entity.Camera;
import com.example.demo.entity.Image;
import com.example.demo.entity.Tag;
import com.example.demo.service.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFetcherServiceImpl implements DataFetcherService {

    private TokenUtil tokenUtil;
    private RestTemplate restTemplate;
    private AuthorService authorService;
    private ImageService imageService;
    private TagService tagService;
    private CameraService cameraService;

    public DataFetcherServiceImpl(TokenUtil tokenUtil, RestTemplate restTemplate, AuthorService authorService, ImageService imageService, TagService tagService, CameraService cameraService) {
        this.tokenUtil = tokenUtil;
        this.restTemplate = restTemplate;
        this.authorService = authorService;
        this.imageService = imageService;
        this.tagService = tagService;
        this.cameraService = cameraService;
    }


    @Override
    public List<ImageDTO> fetchImagesByPage(String page) {

        HttpEntity<String> httpEntity = getCompletedHttpEntity();
        ResponseEntity<RefreshImageResponse> responseEntity;
        if (page == null || page.equals("")) {
            responseEntity = restTemplate.exchange("http://interview.agileengine.com/images", HttpMethod.GET, httpEntity, RefreshImageResponse.class);
        } else {
            responseEntity = restTemplate.exchange("http://interview.agileengine.com/images?page=" + page, HttpMethod.GET, httpEntity, RefreshImageResponse.class);
        }

        List<PictureResponse> pictureResponses = responseEntity.getBody().getPictures();
        List<ImageDTO> imageDTOS = new ArrayList<>();
        for (PictureResponse pictureResponse : pictureResponses) {
            ResponseEntity<ImageResponse> imageResponseResponseEntity = restTemplate.exchange("http://interview.agileengine.com/images/" + pictureResponse.getId(), HttpMethod.GET, httpEntity, ImageResponse.class);
            imageDTOS.add(imageResponseResponseEntity.getBody().mapToImageDTO());
        }


//        Image image = new Image()

//        List<Author> authors = imageDTOS.stream().map(imageDTO -> new Author(imageDTO.getAuthor())).collect(Collectors.toList());
//        TODO

        List<Image> images = mapImageDTOToImage(imageDTOS);
        List<Author> authors = images.stream().map(image -> image.getAuthor()).distinct().collect(Collectors.toList());
        List<Camera> cameras = images.stream().map(image -> image.getCamera()).distinct().collect(Collectors.toList());


        List<List<Tag>> tagsList = images.stream().map(image -> image.getTags()).distinct().collect(Collectors.toList());
        for (List<Tag> tagList: tagsList) {
            tagService.saveIfNotExist(tagList);
        }

        authorService.saveIfNotExist(authors);
        cameraService.saveIfNotExist(cameras);
//        tags.saveAll(authors);

//        imageDTOS.get(0).getAuthor()
//
//        System.out.println(images);
//
//        images.get(0).getAuthor();
        imageService.saveIfNotExist(images);


        return imageDTOS;
    }


    private HttpEntity<String> getCompletedHttpEntity() {
        AgileEngineAuthorizationResponse agileEngineAuthorizationResponse = tokenUtil.retrieveToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + agileEngineAuthorizationResponse.getToken());

        return new HttpEntity<String>(headers);
    }

    private List<Image> mapImageDTOToImage(List<ImageDTO> imageDTOS) {
        return imageDTOS.stream()
                .map(imageDTO -> new Image(
                                imageDTO.getId(),
                                mapToAuthor(imageDTO.getAuthorDTO()),
                                mapToTag(imageDTO.getTags()),
                                mapToCamera(imageDTO.getCameraDTO()),
                                imageDTO.getCropped_picture(),
                                imageDTO.getFull_picture()
                        )
                ).collect(Collectors.toList());
    }

    private List<Tag> mapToTag(List<TagDTO> tags) {
        return tags.stream().map(tagDTO -> new Tag(tagDTO.getTagName())).collect(Collectors.toList());
    }

    private Author mapToAuthor(AuthorDTO authorDTO){
        return new Author(authorDTO.getFullName());

    }

    private Camera mapToCamera(CameraDTO cameraDTO){
        return new Camera(cameraDTO.getCameraName());
    }
//
//    private List<Tag> mapToTag(List<String> tags) {
//        return tags.stream().map(tag -> new Tag(tag)).collect(Collectors.toList());
//    }

}
