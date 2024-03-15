package com.github.plataformadodaleapi.dto.request;

import com.github.plataformadodaleapi.entity.EducationLevel;
import com.github.plataformadodaleapi.entity.GCTrail;

public record StudentRequestDTO(String name,
                                int age,
                                String biography,
                                String linkedin,
                                String profilePicture,
                                String course,
                                EducationLevel educationLevel,
                                GCTrail gcTrail) {
}
