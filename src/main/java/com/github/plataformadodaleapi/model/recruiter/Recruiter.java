package com.github.plataformadodaleapi.model.recruiter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.plataformadodaleapi.model.student.Student;
import com.github.plataformadodaleapi.model.recruiter.dto.request.RecruiterRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "recruiter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "recruiter")
    private List<Student> students;

    public Recruiter(RecruiterRequestDTO dto) {
        this.name = dto.name();
    }
}
