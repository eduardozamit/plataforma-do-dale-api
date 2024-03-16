package com.github.plataformadodaleapi.student.dto.request;

import com.github.plataformadodaleapi.model.student.EducationLevel;
import com.github.plataformadodaleapi.model.student.GCTrail;

import java.util.List;

public record StudentCompetencesIdRequest(String name,
                                          int age,
                                          String biography,
                                          String linkedin,
                                          String profilePicture,
                                          String course,
                                          EducationLevel educationLevel,
                                          GCTrail gcTrail,
                                          List<Long> competences) {
}
