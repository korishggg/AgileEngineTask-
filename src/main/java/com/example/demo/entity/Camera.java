package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Camera {

    @Id
    @Column(unique = true)
    private String cameraName = "";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "camera")
    private List<Image> images;

    public Camera(String cameraName) {
        this.cameraName = cameraName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camera camera = (Camera) o;
        return cameraName.equals(camera.cameraName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cameraName);
    }
}
