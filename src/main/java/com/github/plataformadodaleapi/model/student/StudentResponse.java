package com.github.plataformadodaleapi.model.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentResponse {
    private long id;
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

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.age = student.getAge();
        this.biography = student.getBiography();
        this.linkedin = student.getLinkedin();
        this.profilePicture = student.getProfilePicture();
        this.course = student.getCourse();
        this.educationLevel = student.getEducationLevel().getValue();
        this.gcTrail = student.getGcTrail().getValue();
        this.email = student.getEmail();
        this.city = student.getCity();
        this.courseInstitution = student.getCourseInstitution();
        this.yearOfCourseCompletion = student.getYearOfCourseCompletion();
    }
}
