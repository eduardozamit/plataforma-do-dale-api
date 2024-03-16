package com.github.plataformadodaleapi.controller;

import com.github.plataformadodaleapi.student.dto.request.CompetenceRequest;
import com.github.plataformadodaleapi.model.student.Competence;
import com.github.plataformadodaleapi.service.CompetenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competences")
public class CompetenceController {
    private final CompetenceService service;

    public CompetenceController(CompetenceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Competence> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Competence findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Competence> save(@RequestBody CompetenceRequest competenceRequest) {
        return ResponseEntity.ok(service.save(competenceRequest));
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id) {
        service.deleteCompetenceById(id);
    }
}
