package com.github.plataformadodaleapi.dto;

import com.github.plataformadodaleapi.dto.response.StudentResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTOQuery extends StudentResponse {
    private String competence;
}
