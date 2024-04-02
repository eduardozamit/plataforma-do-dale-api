package com.github.plataformadodaleapi.service;

import com.github.plataformadodaleapi.model.recruiter.RecruiterDetalingData;
import com.github.plataformadodaleapi.model.recruiter.RecruiterModel;
import com.github.plataformadodaleapi.model.recruiter.RegisterRecruiterDTO;
import com.github.plataformadodaleapi.model.recruiter.UpdateRecruiterDTO;
import com.github.plataformadodaleapi.model.student.Student;
import com.github.plataformadodaleapi.model.student.StudentProjection;
import com.github.plataformadodaleapi.model.student.StudentResponseDTO;
import com.github.plataformadodaleapi.repository.RecruiterRepository;
import com.github.plataformadodaleapi.repository.StudentRepository;
import com.github.plataformadodaleapi.security.SecurityFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<StudentResponseDTO> getAllStudents(HttpServletRequest request) {
        Long recruiterId = recoverIdByToken(request);
        List<StudentProjection> results = recruiterRepository.findAllStudents(recruiterId);
        List<StudentResponseDTO> students = new ArrayList<>();

        for (StudentProjection studentProjection : results) {
            StudentResponseDTO student = new StudentResponseDTO();
            student.setId(studentProjection.getId());
            student.setName(studentProjection.getName());
            student.setAge(studentProjection.getAge());
            student.setGcTrail(studentProjection.getGcTrail().getValue());
            student.setLinkedin(studentProjection.getLinkedin());
            student.setEmail(studentProjection.getEmail());
            student.setBiography(studentProjection.getBiography());
            student.setProfilePicture(studentProjection.getProfilePicture());
            student.setCourse(studentProjection.getCourse());
            student.setEducationLevel(studentProjection.getEducationLevel().getValue());
            student.setCity(studentProjection.getCity());
            student.setCourseInstitution(studentProjection.getCourseInstitution());
            student.setYearOfCourseCompletion(studentProjection.getYearOfCourseCompletion());
            student.setFavorited(studentProjection.getFavorited() == 1);
            student.setSoftSkills(studentProjection.getSoftSkills());
            student.setHardSkills(studentProjection.getHardSkills());
            students.add(student);
        }

        return students;
    }

    public List<StudentResponseDTO> getFavoriteStudents(HttpServletRequest request) {
        Long recruiterId = recoverIdByToken(request);
        List<StudentProjection> results = recruiterRepository.findFavoritedStudents(recruiterId);
        List<StudentResponseDTO> students = new ArrayList<>();

        for (StudentProjection studentProjection : results) {
            StudentResponseDTO student = new StudentResponseDTO();
            student.setId(studentProjection.getId());
            student.setName(studentProjection.getName());
            student.setAge(studentProjection.getAge());
            student.setGcTrail(studentProjection.getGcTrail().getValue());
            student.setLinkedin(studentProjection.getLinkedin());
            student.setEmail(studentProjection.getEmail());
            student.setBiography(studentProjection.getBiography());
            student.setProfilePicture(studentProjection.getProfilePicture());
            student.setCourse(studentProjection.getCourse());
            student.setEducationLevel(studentProjection.getEducationLevel().getValue());
            student.setCity(studentProjection.getCity());
            student.setCourseInstitution(studentProjection.getCourseInstitution());
            student.setYearOfCourseCompletion(studentProjection.getYearOfCourseCompletion());
            student.setFavorited(studentProjection.getFavorited() == 1);
            student.setSoftSkills(studentProjection.getSoftSkills());
            student.setHardSkills(studentProjection.getHardSkills());
            students.add(student);
        }

        return students;
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
        Optional<RecruiterModel> recruiter = recruiterRepository.findById(recruiterId);
        if (recruiter.isPresent()) {
            recruiter.get().getFavoriteStudents().removeIf(student -> student.getId() == studentId);
            return new RecruiterDetalingData(recruiterRepository.save(recruiter.get()));
        }
        return null;
    }

    public Long recoverIdByToken(HttpServletRequest request) {
        String tokenJwt = securityFilter.recoverToken(request);
        return tokenService.getIdRecruiter(tokenJwt);
    }

    @Autowired
    public RecruiterService(RecruiterRepository recruiterRepository,
                            StudentRepository studentRepository,
                            PasswordEncoder passwordEncoder,
                            TokenService tokenService,
                            SecurityFilter securityFilter) {
        this.recruiterRepository = recruiterRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.securityFilter = securityFilter;
    }
}