package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.model.student.Student;

import java.util.List;

public interface StudentRepositoryCustom {
    List<Student> getWithFilter(StudentFilterParam params);
}
