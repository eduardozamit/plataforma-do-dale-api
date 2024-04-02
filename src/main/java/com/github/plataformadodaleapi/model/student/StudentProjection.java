package com.github.plataformadodaleapi.model.student;

import java.util.List;

public interface StudentProjection {
    Long getId();

    String getName();

    int getAge();

    GCTrail getGcTrail();

    String getLinkedin();

    String getEmail();

    String getBiography();

    String getProfilePicture();

    String getCourse();

    EducationLevel getEducationLevel();

    String getCity();

    String getCourseInstitution();

    String getYearOfCourseCompletion();

    Long getFavorited();

    List<String> getSoftSkills();

    List<String> getHardSkills();
}
