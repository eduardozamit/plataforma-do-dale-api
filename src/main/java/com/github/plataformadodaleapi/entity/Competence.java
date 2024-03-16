package com.github.plataformadodaleapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.plataformadodaleapi.dto.request.CompetenceRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "competence")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "description", nullable = false)
    private String description;
    @JsonBackReference
    @ManyToMany(mappedBy = "competences")
    private List<Student> students;

    public Competence(String description) {
        this.description = description;
    }

    public Competence(CompetenceRequest competenceRequest) {
        this.description = competenceRequest.description();
    }
}
