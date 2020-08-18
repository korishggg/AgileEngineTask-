package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public class Camera {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)

//    @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
//    private String id;

    @Column(unique = true)
    private String cameraName = "";

    @OneToOne(mappedBy = "camera")
    private Image image;

    public Camera(String cameraName) {
        this();
        this.cameraName = cameraName;
    }

    public Camera() {
//        this.id = UUID.randomUUID().toString();
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
