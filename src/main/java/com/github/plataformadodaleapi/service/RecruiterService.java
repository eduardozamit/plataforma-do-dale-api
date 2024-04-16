package com.github.plataformadodaleapi.service;

import com.github.plataformadodaleapi.model.recruiter.RecruiterDetalingData;
import com.github.plataformadodaleapi.model.recruiter.RecruiterModel;
import com.github.plataformadodaleapi.model.recruiter.RegisterRecruiterDTO;
import com.github.plataformadodaleapi.model.recruiter.UpdateRecruiterDTO;
import com.github.plataformadodaleapi.model.skills.SkillRequestDTO;
import com.github.plataformadodaleapi.model.student.StudentProjection;
import com.github.plataformadodaleapi.model.student.StudentResponseDTO;
import com.github.plataformadodaleapi.repository.RecruiterRepository;
import com.github.plataformadodaleapi.security.SecurityFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruiterService {
    private RecruiterRepository recruiterRepository;
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

    @Transactional
    public boolean favoriteOrDisfavorStudent(HttpServletRequest request, Long studentId) {
        if (recruiterRepository.existStudentById(studentId) == 1) {
            Long recruiterId = recoverIdByToken(request);
            if (recruiterRepository.studentIsFavorited(studentId, recruiterId) == 1) {
                recruiterRepository.disfavorStudentById(studentId, recruiterId);
            } else {
                recruiterRepository.favoriteStudentById(studentId, recruiterId);
            }
            return true;
        }
        return false;
    }

    public List<StudentResponseDTO> getAllStudents(HttpServletRequest request) {
        Long recruiterId = recoverIdByToken(request);
        List<StudentProjection> results = recruiterRepository.findAllStudents(recruiterId);
        return convertToStudentResponseDTOList(results);
    }

    public List<StudentResponseDTO> getFavoriteStudents(HttpServletRequest request) {
        Long recruiterId = recoverIdByToken(request);
        List<StudentProjection> results = recruiterRepository.findFavoritedStudents(recruiterId);
        return convertToStudentResponseDTOList(results);
    }

    private List<StudentResponseDTO> convertToStudentResponseDTOList(List<StudentProjection> results) {
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
            student.setSoftSkills(convertSkills(studentProjection.getSoftSkills()));
            student.setHardSkills(convertSkills(studentProjection.getHardSkills()));
            students.add(student);
        }
        return students;
    }

    public List<SkillRequestDTO> convertSkills(String skillsString) {
        List<SkillRequestDTO> skills = new ArrayList<>();
        if (skillsString != null && !skillsString.isEmpty()) {
            String[] skillsArray = skillsString.split(",");
            for (String skill : skillsArray) {
                SkillRequestDTO skillRequestDTO = new SkillRequestDTO(skill.trim());
                skills.add(skillRequestDTO);
            }
        }
        return skills;
    }

    public Long recoverIdByToken(HttpServletRequest request) {
        String tokenJwt = securityFilter.recoverToken(request);
        return tokenService.getIdRecruiter(tokenJwt);
    }

    @Autowired
    public RecruiterService(RecruiterRepository recruiterRepository,
                            PasswordEncoder passwordEncoder,
                            TokenService tokenService,
                            SecurityFilter securityFilter) {
        this.recruiterRepository = recruiterRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.securityFilter = securityFilter;
    }
}