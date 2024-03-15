package com.github.plataformadodaleapi.controller;

import com.github.plataformadodaleapi.dto.StudentDTOQuery;
import com.github.plataformadodaleapi.dto.request.StudentRequestDTO;
import com.github.plataformadodaleapi.dto.response.StudentCompetenceResponse;
import com.github.plataformadodaleapi.dto.response.StudentResponse;
import com.github.plataformadodaleapi.entity.Student;
import com.github.plataformadodaleapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService service;

    @PostMapping("/salvar")
    public Student saveStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        return service.saveStudent(studentRequestDTO);
    }

    @GetMapping
    public List<StudentResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/with-competence/{id}")
    public List<StudentDTOQuery> findStudentCompetenceById(@PathVariable Long id) {
        return service.findStudentWithCompetenceById(id);
    }

    @GetMapping("/teste/{id}")
    public StudentCompetenceResponse listStudentCompetenceById(@PathVariable Long id) {
        return service.findStudentCompetenceById(id);
    }

    @PostMapping("/salvar-competencia")
    public Student saveStudentCompetence(@RequestBody StudentRequestDTO studentRequestDTO, @RequestParam Long idCompetence) {
        return service.saveStudentCompetence(studentRequestDTO, idCompetence);
    }

    @PostMapping("/adicionar-competencia")
    public Student addCompetence(@RequestParam("student") String studentId, @RequestParam("competence") String idCompetence) {
        return service.addCompetence(Long.parseLong(studentId), Long.parseLong(idCompetence));
    }

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }
}