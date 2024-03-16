package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.model.student.GCTrail;
import lombok.Data;

import java.util.List;

@Data
public class StudentFilterParam {
    private String name;
    private Integer age;
    private List<Long> competences;
    private GCTrail gcTrail;
}
