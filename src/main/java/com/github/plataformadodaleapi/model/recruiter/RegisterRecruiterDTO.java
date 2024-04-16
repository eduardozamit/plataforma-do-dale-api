package com.github.plataformadodaleapi.model.recruiter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRecruiterDTO(
        @NotBlank
        String name,
        String company,
        @Email
        String email,
        @NotBlank
        String password) {
}