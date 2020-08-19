package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Tag.class)
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Camera camera;
    private String cropped_picture;
    private String full_picture;

    public Image(String id, String cropped_picture, String full_picture) {
        this.id = id;
        this.cropped_picture = cropped_picture;
        this.full_picture = full_picture;
    }


    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", author=" + author.getFullName() +
                ", tags=" + tags +
                ", camera=" + camera.getCameraName() +
                ", cropped_picture='" + cropped_picture + '\'' +
                ", full_picture='" + full_picture + '\'' +
                '}';
    }
}
