package com.github.plataformadodaleapi.model.student;

import com.github.plataformadodaleapi.model.skills.SkillRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentResponseDTO {
    private Long id;
    private String name;
    private int age;
    private String gcTrail;
    private String linkedin;
    private String email;
    private String biography;
    private String profilePicture;
    private String course;
    private String educationLevel;
    private String city;
    private String courseInstitution;
    private String yearOfCourseCompletion;
    private boolean favorited;
    private List<SkillRequestDTO> softSkills;
    private List<SkillRequestDTO> hardSkills;
}
