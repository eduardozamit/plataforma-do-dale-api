package com.github.plataformadodaleapi.service;

import com.github.plataformadodaleapi.model.recruiter.Recruiter;
import com.github.plataformadodaleapi.model.recruiter.RecruiterRequestDTO;
import com.github.plataformadodaleapi.model.student.Student;
import com.github.plataformadodaleapi.repository.RecruiterRepository;
import com.github.plataformadodaleapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public RecruiterService(RecruiterRepository recruiterRepository, StudentRepository studentRepository) {
        this.recruiterRepository = recruiterRepository;
        this.studentRepository = studentRepository;
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

    public List<Student> getFavoriteStudentsById(Long id) {
        Optional<Recruiter> recruiterFounded = recruiterRepository.findById(id);
        return recruiterFounded.map(Recruiter::getFavoriteStudents).orElse(null);
    }

    public Recruiter favoriteStudentById(Long recruiterId, Long studentId) {
        Optional<Recruiter> recruiterOptional = recruiterRepository.findById(recruiterId);
        if (recruiterOptional.isPresent()) {
            Optional<Student> studentOptional = studentRepository.findById(studentId);
            if (studentOptional.isPresent()) {
                recruiterOptional.get().getFavoriteStudents().add(studentOptional.get());
                return recruiterRepository.save(recruiterOptional.get());
            }
        }
        return null;
    }

    public Recruiter disfavorStudentById(Long recruiterId, Long studentId) {
        Optional<Recruiter> recruiterFounded = recruiterRepository.findById(recruiterId);
        if (recruiterFounded.isPresent()) {
            recruiterFounded.get().getFavoriteStudents().removeIf(student -> student.getId() == studentId);
            return recruiterRepository.save(recruiterFounded.get());
        }
        return null;
    }
}
