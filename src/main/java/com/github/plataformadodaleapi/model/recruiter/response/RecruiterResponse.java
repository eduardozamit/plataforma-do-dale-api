package com.github.plataformadodaleapi.model.recruiter.response;

import com.github.plataformadodaleapi.model.recruiter.Recruiter;
import com.github.plataformadodaleapi.model.student.Student;

import java.util.ArrayList;
import java.util.List;

public class RecruiterResponse {
    private Long id;
    private String name;
    private List<Student> favoritedStudents;

    public RecruiterResponse(Recruiter recruiter) {
        this.id = recruiter.getId();
        this.name = recruiter.getName();
        this.favoritedStudents = new ArrayList<Student>();
    }
}
