package com.github.plataformadodaleapi.model.recruiter;

import com.github.plataformadodaleapi.model.student.Student;

import java.util.List;

public record RecruiterDetalingData(long id, String name, String company, List<Student> favoriteStudents) {

    public RecruiterDetalingData(RecruiterModel recruiterModel) {
        this(recruiterModel.getId(), recruiterModel.getName(), recruiterModel.getCompany(), recruiterModel.getFavoriteStudents());
    }
}