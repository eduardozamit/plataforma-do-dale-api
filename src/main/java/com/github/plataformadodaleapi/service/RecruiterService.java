package com.github.plataformadodaleapi.service;

import com.github.plataformadodaleapi.model.recruiter.RecruiterDetalingData;
import com.github.plataformadodaleapi.model.recruiter.RecruiterModel;
import com.github.plataformadodaleapi.model.recruiter.RegisterRecruiterDTO;
import com.github.plataformadodaleapi.model.recruiter.UpdateRecruiterDTO;
import com.github.plataformadodaleapi.model.student.Student;
import com.github.plataformadodaleapi.repository.RecruiterRepository;
import com.github.plataformadodaleapi.repository.StudentRepository;
import com.github.plataformadodaleapi.security.SecurityFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService {
    private RecruiterRepository recruiterRepository;
    private StudentRepository studentRepository;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;
    private SecurityFilter securityFilter;

    public RecruiterDetalingData createRecruiter(RegisterRecruiterDTO dto) {
        RecruiterModel recruiterModel = new RecruiterModel(dto);
        recruiterModel.setPassword(passwordEncoder.encode(dto.password()));
        recruiterRepository.save(recruiterModel);
        return new RecruiterDetalingData(recruiterModel);
    }

    public RecruiterDetalingData updateRecruiter(UpdateRecruiterDTO updateDTO, HttpServletRequest request) {
        Long id = recoverIdByToken(request);
        RecruiterModel recruiterModel = recruiterRepository.getReferenceById(id);
        recruiterModel.updateInformation(updateDTO);
        return new RecruiterDetalingData(recruiterModel);
    }

    public RecruiterDetalingData getRecruiterProfile(HttpServletRequest request) {
        Long id = recoverIdByToken(request);
        RecruiterModel recruiterModel = recruiterRepository.getReferenceById(id);
        return new RecruiterDetalingData(recruiterModel);
    }

    public void deleteRecruiter(HttpServletRequest request) {
        Long id = recoverIdByToken(request);
        recruiterRepository.deleteById(id);
    }

    public List<Student> getFavoriteStudents(HttpServletRequest request) {
        Long id = recoverIdByToken(request);
        Optional<RecruiterModel> recruiterFounded = recruiterRepository.findById(id);
        return recruiterFounded.map(RecruiterModel::getFavoriteStudents).orElse(null);
    }

    public RecruiterDetalingData favoriteStudent(HttpServletRequest request, Long studentId) {
        Long recruiterId = recoverIdByToken(request);
        Optional<RecruiterModel> recruiterOptional = recruiterRepository.findById(recruiterId);
        if (recruiterOptional.isPresent()) {
            Optional<Student> studentOptional = studentRepository.findById(studentId);
            if (studentOptional.isPresent()) {
                recruiterOptional.get().getFavoriteStudents().add(studentOptional.get());
                return new RecruiterDetalingData(recruiterRepository.save(recruiterOptional.get()));
            }
        }
        return null;
    }

    public RecruiterDetalingData disfavorStudent(HttpServletRequest request, Long studentId) {
        Long recruiterId = recoverIdByToken(request);
        Optional<RecruiterModel> recruiterFounded = recruiterRepository.findById(recruiterId);
        if (recruiterFounded.isPresent()) {
            recruiterFounded.get().getFavoriteStudents().removeIf(student -> student.getId() == studentId);
            return new RecruiterDetalingData(recruiterRepository.save(recruiterFounded.get()));
        }
        return null;
    }

    public Long recoverIdByToken(HttpServletRequest request) {
        String tokenJwt = securityFilter.recoverToken(request);

        return tokenService.getIdRecruiter(tokenJwt);
    }

    @Autowired
    public RecruiterService(RecruiterRepository recruiterRepository, StudentRepository studentRepository, PasswordEncoder passwordEncoder, TokenService tokenService, SecurityFilter securityFilter) {
        this.recruiterRepository = recruiterRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.securityFilter = securityFilter;
    }
}