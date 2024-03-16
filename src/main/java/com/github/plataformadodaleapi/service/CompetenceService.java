package com.github.plataformadodaleapi.service;

import com.github.plataformadodaleapi.student.dto.request.CompetenceRequest;
import com.github.plataformadodaleapi.model.student.Competence;
import com.github.plataformadodaleapi.repository.CompetenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService {
    private final CompetenceRepository repository;

    public CompetenceService(CompetenceRepository repository) {
        this.repository = repository;
    }

    public Competence save(CompetenceRequest competenceRequest) {
        return repository.save(new Competence(competenceRequest));
    }

    public Competence findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Competence> findAll() {
        return repository.findAll();
    }

    public void deleteCompetenceById(Long id) {
        repository.deleteById(id);
    }
}
