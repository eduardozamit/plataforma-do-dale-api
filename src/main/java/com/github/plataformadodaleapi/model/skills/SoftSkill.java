package com.github.plataformadodaleapi.model.skills;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.plataformadodaleapi.model.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "soft_skill")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SoftSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "description", nullable = false)
    private String description;
    @JsonBackReference
    @ManyToMany(mappedBy = "softSkills")
    private List<Student> students;

    public SoftSkill(String description) {
        this.description = description;
    }

    public SoftSkill(SkillRequestDTO skillRequestDTO) {
        this.description = skillRequestDTO.description();
    }
}
