package com.github.plataformadodaleapi.dto.request;

import com.github.plataformadodaleapi.entity.EducationLevel;
import com.github.plataformadodaleapi.entity.GCTrail;

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
