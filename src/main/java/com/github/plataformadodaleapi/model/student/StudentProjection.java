package com.github.plataformadodaleapi.model.student;

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

    String getSoftSkills();

    String getHardSkills();
}
