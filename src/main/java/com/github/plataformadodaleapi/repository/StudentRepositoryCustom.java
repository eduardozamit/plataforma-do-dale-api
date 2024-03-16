package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.entity.Student;

import java.util.List;

public interface StudentRepositoryCustom {
    List<Student> getWithFilter(StudentFilterParam params);
}
