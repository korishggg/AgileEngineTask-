package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Author {

    @Id
    @Column(unique = true)
    private String fullName = "";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Image> images;

    public Author(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return fullName.equals(author.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }
}

