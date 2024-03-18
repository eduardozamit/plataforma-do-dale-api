package com.github.plataformadodaleapi.model.skills;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.plataformadodaleapi.model.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "hard_skill")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HardSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "description", nullable = false)
    private String description;
    @JsonBackReference
    @ManyToMany(mappedBy = "hardSkills")
    private List<Student> students;

    public HardSkill(String description) {
        this.description = description;
    }

    public HardSkill(SkillRequestDTO skillRequestDTO) {
        this.description = skillRequestDTO.description();
    }
}
