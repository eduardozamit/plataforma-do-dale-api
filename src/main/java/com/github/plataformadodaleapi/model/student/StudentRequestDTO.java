package com.github.plataformadodaleapi.model.student;

public record StudentRequestDTO(String name,
                                int age,
                                String email,
                                String biography,
                                String city,
                                String linkedin,
                                String profilePicture,
                                String course,
                                String courseInstitution,
                                String yearOfCourseCompletion,
                                EducationLevel educationLevel,
                                GCTrail gcTrail) {
}
