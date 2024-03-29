package com.github.plataformadodaleapi.controller;

import com.github.plataformadodaleapi.model.recruiter.Recruiter;
import com.github.plataformadodaleapi.model.recruiter.RecruiterRequestDTO;
import com.github.plataformadodaleapi.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    private final RecruiterService recruiterService;

    @Autowired
    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @GetMapping
    public ResponseEntity<List<Recruiter>> getAllRecruiters() {
        List<Recruiter> recruiters = recruiterService.getAllRecruiters();
        return ResponseEntity.ok(recruiters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recruiter> getRecruiterById(@PathVariable Long id) {
        Recruiter recruiter = recruiterService.getRecruiterById(id);
        return recruiter != null
                ? ResponseEntity.ok(recruiter)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Recruiter> createRecruiter(@RequestBody RecruiterRequestDTO requestDto) {
        Recruiter recruiter = recruiterService.createRecruiter(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(recruiter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recruiter> updateRecruiter(@PathVariable Long id, @RequestBody RecruiterRequestDTO requestDto) {
        Recruiter recruiterFounded = recruiterService.updateRecruiter(id, requestDto);
        return recruiterFounded != null
                ? ResponseEntity.ok(recruiterFounded)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecruiter(@PathVariable Long id) {
        recruiterService.deleteRecruiter(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add-favorite-student")
    public ResponseEntity<Recruiter> favoriteStudent(@RequestParam("recruiter") Long recruiterId,
                                                     @RequestParam("student") Long studentId) {
        Recruiter recruiter = recruiterService.favoriteStudentById(recruiterId, studentId);
        return recruiter != null
                ? ResponseEntity.ok(recruiter)
                : ResponseEntity.badRequest().build();
    }

    @PostMapping("/disfavor-student")
    public ResponseEntity<Recruiter> disfavorStudent(@RequestParam("recruiter") Long recruiterId,
                                                     @RequestParam("student") Long studentId) {
        Recruiter updatedRecruiter = recruiterService.disfavorStudentById(recruiterId, studentId);
        return updatedRecruiter != null
                ? ResponseEntity.ok(updatedRecruiter)
                : ResponseEntity.notFound().build();
    }
}
