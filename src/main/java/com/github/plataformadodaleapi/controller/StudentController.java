package com.github.plataformadodaleapi.controller;

import com.github.plataformadodaleapi.model.student.Student;
import com.github.plataformadodaleapi.model.student.StudentRequestDTO;
import com.github.plataformadodaleapi.model.student.StudentResponse;
import com.github.plataformadodaleapi.repository.StudentFilterParam;
import com.github.plataformadodaleapi.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        Student student = studentService.saveStudent(studentRequestDTO);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PostMapping("/add-hard-skill")
    public ResponseEntity<Student> addHardSkill(@RequestParam("student") Long studentId,
                                                @RequestParam("hardSkill") Long hardSkillId) {
        Student student = studentService.addHardSkillToStudent(studentId, hardSkillId);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/add-hard-skill/more-than-one")
    public ResponseEntity<Student> addHardSkills(@RequestParam("student") Long studentId,
                                                 @RequestParam("hardSkill") List<Long> idsHardSkills) {
        Student student = studentService.addManyHardSkillsToStudent(studentId, idsHardSkills);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/remove-hard-skill")
    public ResponseEntity<Student> removeHardSkillById(@RequestParam("student") Long studentId,
                                                       @RequestParam("hardSkill") Long hardSkillId) {
        Student studentUpdated = studentService.removeHardSkillById(studentId, hardSkillId);
        return studentUpdated != null ? ResponseEntity.ok(studentUpdated) : ResponseEntity.notFound().build();
    }


    @PostMapping("/add-soft-skill")
    public ResponseEntity<Student> addSoftSkill(@RequestParam("student") Long studentId,
                                                @RequestParam("softSkill") Long softSkillId) {
        Student student = studentService.addSoftSkillToStudent(studentId, softSkillId);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/add-soft-skill/more-than-one")
    public ResponseEntity<Student> addSoftSkills(@RequestParam("student") Long studentId,
                                                 @RequestParam("softSkill") List<Long> softSkillsids) {
        Student student = studentService.addManySoftSkillsToStudent(studentId, softSkillsids);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/remove-soft-skill")
    public ResponseEntity<Student> removeSoftSkillById(@RequestParam("student") Long studentId,
                                                       @RequestParam("softSkill") Long softSkillId) {
        Student studentUpdated = studentService.removeSoftSkillById(studentId, softSkillId);
        return studentUpdated != null ? ResponseEntity.ok(studentUpdated) : ResponseEntity.notFound().build();
    }


    @GetMapping
    public ResponseEntity<List<StudentResponse>> findAllStudents() {
        List<StudentResponse> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Student>> getWithFilter(@RequestBody StudentFilterParam params) {
        List<Student> students = studentService.getAllByFilter(params);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudentWithCompetenceById(@PathVariable Long id) {
        Optional<Student> studentOptional = studentService.getStudentWithCompetenceById(id);
        return studentOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
