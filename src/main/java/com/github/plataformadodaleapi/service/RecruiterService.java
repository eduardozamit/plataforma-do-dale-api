package com.github.plataformadodaleapi.service;

import com.github.plataformadodaleapi.model.recruiter.Recruiter;
import com.github.plataformadodaleapi.model.recruiter.dto.request.RecruiterRequestDTO;
import com.github.plataformadodaleapi.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;

    @Autowired
    public RecruiterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    public Recruiter getRecruiterById(Long id) {
        Optional<Recruiter> recruiterFounded = recruiterRepository.findById(id);
        return recruiterFounded.orElse(null);
    }

    public Recruiter createRecruiter(RecruiterRequestDTO requestDTO) {
        return recruiterRepository.save(new Recruiter(requestDTO));
    }

    public Recruiter updateRecruiter(Long id, RecruiterRequestDTO requestDTO) {
        Optional<Recruiter> recruiterFounded = recruiterRepository.findById(id);
        if (recruiterFounded.isPresent()) {
            recruiterFounded.get().setName(requestDTO.name());
            return recruiterRepository.save(recruiterFounded.get());
        }
        return null;
    }

    public void deleteRecruiter(Long id) {
        recruiterRepository.deleteById(id);
    }
}
