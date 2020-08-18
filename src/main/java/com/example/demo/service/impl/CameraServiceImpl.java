package com.example.demo.service.impl;

import com.example.demo.entity.Author;
import com.example.demo.entity.Camera;
import com.example.demo.repository.CameraRepository;
import com.example.demo.service.CameraService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CameraServiceImpl implements CameraService {

private CameraRepository cameraRepository;

    public CameraServiceImpl(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    @Override
    public List<Camera> saveIfNotExist(List<Camera> cameras) {
        List<Camera> list = cameraRepository.findAll();
        List<Camera> toSave = cameras.stream().filter(camera -> !list.contains(camera)).collect(Collectors.toList());

        cameraRepository.saveAll(toSave);
        return toSave;
    }
}
