package com.example.demo.api;

import com.example.demo.service.DataFetcherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("refresh")
public class RefreshRestController {

    private DataFetcherService dataFetcherService;

    public RefreshRestController(DataFetcherService dataFetcherService) {
        this.dataFetcherService = dataFetcherService;
    }

    @PostMapping("/images")
    public ResponseEntity<?> fetchImages(@RequestParam String page){
        return new ResponseEntity<>(dataFetcherService.fetchImagesByPage(page), HttpStatus.OK);
    }


}
