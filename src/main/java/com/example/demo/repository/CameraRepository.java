package com.example.demo.repository;

import com.example.demo.entity.Author;
import com.example.demo.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CameraRepository extends JpaRepository<Camera, String> {
}
