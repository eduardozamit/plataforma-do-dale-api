package com.github.plataformadodaleapi.service;

import com.github.plataformadodaleapi.model.student.Student;
import com.github.plataformadodaleapi.model.student.StudentRequestDTO;
import com.github.plataformadodaleapi.model.student.StudentResponse;
import com.github.plataformadodaleapi.repository.HardSkillRepository;
import com.github.plataformadodaleapi.repository.SoftSkillRepository;
import com.github.plataformadodaleapi.repository.StudentFilterParam;
import com.github.plataformadodaleapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final HardSkillRepository hardSkillRepository;
    private final SoftSkillRepository softSkillRepository;

    public StudentService(StudentRepository studentRepository, HardSkillRepository hardSkillRepository, SoftSkillRepository softSkillRepository) {
        this.studentRepository = studentRepository;
        this.hardSkillRepository = hardSkillRepository;
        this.softSkillRepository = softSkillRepository;
    }

    public Student saveStudent(StudentRequestDTO studentRequestDTO) {
        Student student = new Student(studentRequestDTO);
        return studentRepository.save(student);
    }


    public List<StudentResponse> getAllStudents() {
        List<Student> studentsList = studentRepository.findAll();
        return studentsList.stream().map(StudentResponse::new).toList();
    }

    public Optional<StudentResponse> getStudentWithCompetenceById(long studentId) {
        return studentRepository.findById(studentId).map(StudentResponse::new);
    }

    public List<StudentResponse> getAllByFilter(StudentFilterParam params) {
        List<Student> studentsList = studentRepository.getWithFilter(params);
        return studentsList.stream().map(StudentResponse::new).toList();
    }

// Comentado para reutilizar numa nova classe de Administração
//    public Student addSoftSkillToStudent(Long studentId, Long softSkillId) {
//        Optional<Student> studentFound = studentRepository.findById(studentId);
//        if (studentFound.isPresent()) {
//            Optional<SoftSkill> softSkillFound = softSkillRepository.findById(softSkillId);
//            softSkillFound.ifPresent(softSkill -> studentFound.get().getSoftSkills().add(softSkill));
//            return studentRepository.save(studentFound.get());
//        }
//        return null;
//    }
//
//    public Student removeSoftSkillById(Long studentId, Long softSkillId) {
//        Optional<Student> studentFound = studentRepository.findById(studentId);
//        if (studentFound.isPresent()) {
//            studentFound.get().getSoftSkills().removeIf(softSkill -> softSkill.getId() == softSkillId);
//            return studentRepository.save(studentFound.get());
//        }
//        return null;
//    }
//    public Student addManySoftSkillsToStudent(Long studentId, List<Long> listOfSoftSkills) {
//        Optional<Student> studentFound = studentRepository.findById(studentId);
//        if (studentFound.isPresent()) {
//            List<SoftSkill> softSkills = new ArrayList<>();
//            for (Long id : listOfSoftSkills) {
//                Optional<SoftSkill> softSkillFound = softSkillRepository.findById(id);
//                softSkillFound.ifPresent(softSkills::add);
//            }
//            studentFound.get().getSoftSkills().addAll(softSkills);
//            return studentRepository.save(studentFound.get());
//        }
//        return null;
//    }
    //    public Student addManyHardSkillsToStudent(Long studentId, List<Long> listOfHardSkills) {
//        Optional<Student> studentFound = studentRepository.findById(studentId);
//        if (studentFound.isPresent()) {
//            List<HardSkill> hardSkills = new ArrayList<>();
//            for (Long id : listOfHardSkills) {
//                Optional<HardSkill> hardSkillFound = hardSkillRepository.findById(id);
//                hardSkillFound.ifPresent(hardSkills::add);
//            }
//            studentFound.get().getHardSkills().addAll(hardSkills);
//            return studentRepository.save(studentFound.get());
//        }
//        return null;
//    }
//
//    public Student addHardSkillToStudent(Long studentId, Long hardSkillId) {
//        Optional<Student> studentFound = studentRepository.findById(studentId);
//        if (studentFound.isPresent()) {
//            Optional<HardSkill> hardSkillFound = hardSkillRepository.findById(hardSkillId);
//            hardSkillFound.ifPresent(hardSkill -> studentFound.get().getHardSkills().add(hardSkill));
//            return studentRepository.save(studentFound.get());
//        }
//        return null;
//    }
//
//    public Student removeHardSkillById(Long studentId, Long hardSkillId) {
//        Optional<Student> studentFound = studentRepository.findById(studentId);
//        if (studentFound.isPresent()) {
//            studentFound.get().getHardSkills().removeIf(hardSkill -> hardSkill.getId() == hardSkillId);
//            return studentRepository.save(studentFound.get());
//        }
//        return null;
//    }
}