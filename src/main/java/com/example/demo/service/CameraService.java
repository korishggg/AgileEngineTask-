package com.example.demo.service;

import com.example.demo.entity.Camera;

import java.util.List;

public interface CameraService {
    List<Camera> saveIfNotExist(List<Camera> cameras);
}
