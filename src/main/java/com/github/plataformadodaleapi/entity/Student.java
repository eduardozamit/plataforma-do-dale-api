package com.github.plataformadodaleapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.plataformadodaleapi.dto.request.StudentRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Table(name = "student")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age", length = 3, nullable = false)
    private int age;
    @Column(name = "biography", length = 1200)
    private String biography;
    @Column(name = "linkedin")
    private String linkedin;
    @Column(name = "profile_picture", length = 1000)
    private String profilePicture;
    @Column(name = "course")
    private String course;
    @Column(name = "education_level", length = 24, nullable = false)
    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;
    @Column(name = "gc_trail", length = 54, nullable = false)
    @Enumerated(EnumType.STRING)
    private GCTrail gcTrail;

    @JsonManagedReference
    @Cascade(value = CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "student_competence",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private List<Competence> competences;

    public Student(StudentRequestDTO studentRequestDTO) {
        this.name = studentRequestDTO.name();
        this.age = studentRequestDTO.age();
        this.biography = studentRequestDTO.biography();
        this.linkedin = studentRequestDTO.linkedin();
        this.profilePicture = studentRequestDTO.profilePicture();
        this.course = studentRequestDTO.course();
        this.educationLevel = studentRequestDTO.educationLevel();
        this.gcTrail = studentRequestDTO.gcTrail();
    }
}