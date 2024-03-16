package com.github.plataformadodaleapi.controller;

import com.github.plataformadodaleapi.dto.request.StudentRequestDTO;
import com.github.plataformadodaleapi.dto.response.StudentResponse;
import com.github.plataformadodaleapi.entity.GCTrail;
import com.github.plataformadodaleapi.entity.Student;
import com.github.plataformadodaleapi.service.StudentService;
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

    @GetMapping
    public List<StudentResponse> findAllStudents() {
        return service.findAllStudents();
    }

    @GetMapping("/with-competence/{id}")
    public ResponseEntity<Student> findStudentWithCompetenceById(@PathVariable long id) {
        return service.listStudentWithCompetenceById(id).isPresent()
                ? ResponseEntity.ok(service.listStudentWithCompetenceById(id).get())
                : ResponseEntity.badRequest().build();
    }

    @PostMapping("/salvar")
    public Student saveStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        return service.saveStudent(studentRequestDTO);
    }

    @PostMapping("/adicionar-competencia")
    public Student addCompetence(@RequestParam("student") String studentId, @RequestParam("competence") String idCompetence) {
        return service.addCompetenceToStudent(studentId, idCompetence);
    }

    @PostMapping("/adicionar-competencia/mais-de-uma")
    public ResponseEntity<Student> addCompetences(@RequestParam("student") String studentId, @RequestParam("competence") List<String> idsCompetences) {
        Optional<Student> student = service.addManyCompetencesToStudent(studentId, idsCompetences);
        return student.isPresent() ? ResponseEntity.ok(student.get()) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/filtro")
    public List<Student> findStudentsByFilter(@RequestParam(required = false, name = "name") String name,
                                              @RequestParam(required = false, name = "age") Integer age,
                                              @RequestParam(required = false, name = "trail") GCTrail gcTrail) {
        return service.listStudentsByFilter(name, age, gcTrail);
    }
}