package com.github.plataformadodaleapi.controller;

import com.github.plataformadodaleapi.dto.request.StudentRequestDTO;
import com.github.plataformadodaleapi.dto.response.StudentResponse;
import com.github.plataformadodaleapi.entity.Student;
import com.github.plataformadodaleapi.repository.StudentFilterParam;
import com.github.plataformadodaleapi.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/salvar")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        Student student = service.saveStudent(studentRequestDTO);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PostMapping("/adicionar-competencia/mais-de-uma")
    public ResponseEntity<Student> addCompetences(@RequestParam("student") Long studentId,
                                                  @RequestParam("competence") List<Long> idsCompetences) {
        Student student = service.addManyCompetencesToStudent(studentId, idsCompetences);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/adicionar-competencia")
    public ResponseEntity<Student> addCompetence(@RequestParam("student") Long studentId,
                                                 @RequestParam("competence") Long idCompetence) {
        Student student = service.addCompetenceToStudent(studentId, idCompetence);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/sem-competencias")
    public ResponseEntity<List<StudentResponse>> findAllStudents() {
        List<StudentResponse> students = service.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudentsWithCompetences() {
        List<Student> students = service.getAllStudentsWithCompetence();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Student>> getWithFilter(@RequestBody StudentFilterParam params) {
        List<Student> students = service.getAllByFilter(params);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/with-competence/{id}")
    public ResponseEntity<Student> findStudentWithCompetenceById(@PathVariable Long id) {
        Optional<Student> studentOptional = service.getStudentWithCompetenceById(id);
        return studentOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
