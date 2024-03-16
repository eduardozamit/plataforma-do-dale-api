package com.github.plataformadodaleapi.dto.response;

import com.github.plataformadodaleapi.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentCompetenceResponse extends StudentResponse {
    private List<String> competences;

    public StudentCompetenceResponse(Student student) {
        super(student);
    }

    public StudentCompetenceResponse(Student student, List<String> competences) {
        super(student);
        this.competences.addAll(competences);
    }
}
