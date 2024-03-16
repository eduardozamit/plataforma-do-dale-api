package com.github.plataformadodaleapi.student.dto.response;

import com.github.plataformadodaleapi.model.student.EducationLevel;
import com.github.plataformadodaleapi.model.student.GCTrail;
import com.github.plataformadodaleapi.model.student.Student;
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
    private String biography;
    private String linkedin;
    private String profilePicture;
    private String course;
    private EducationLevel educationLevel;
    private GCTrail gcTrail;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.age = student.getAge();
        this.biography = student.getBiography();
        this.linkedin = student.getLinkedin();
        this.profilePicture = student.getProfilePicture();
        this.course = student.getCourse();
        this.educationLevel = student.getEducationLevel();
        this.gcTrail = student.getGcTrail();
    }
}
