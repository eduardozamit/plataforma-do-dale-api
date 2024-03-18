package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.model.student.EducationLevel;
import com.github.plataformadodaleapi.model.student.GCTrail;
import lombok.Data;

import java.util.List;

@Data
public class StudentFilterParam {
    private String name;
    private Integer age;
    private String city;
    private List<Long> hardSkill;
    private List<Long> softSkill;
    private GCTrail gcTrail;
    private EducationLevel educationLevel;
}
