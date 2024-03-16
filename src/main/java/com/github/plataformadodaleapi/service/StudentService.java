package com.github.plataformadodaleapi.service;

import com.github.plataformadodaleapi.model.student.Competence;
import com.github.plataformadodaleapi.model.student.Student;
import com.github.plataformadodaleapi.repository.CompetenceRepository;
import com.github.plataformadodaleapi.repository.StudentFilterParam;
import com.github.plataformadodaleapi.repository.StudentRepository;
import com.github.plataformadodaleapi.student.dto.request.StudentRequestDTO;
import com.github.plataformadodaleapi.student.dto.response.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private CompetenceRepository competenceRepository;

    public StudentService(StudentRepository studentRepository, CompetenceRepository competenceRepository) {
        this.studentRepository = studentRepository;
        this.competenceRepository = competenceRepository;
    }

    public Student saveStudent(StudentRequestDTO studentRequestDTO) {
        Student student = new Student(studentRequestDTO);
        return studentRepository.save(student);
    }

    public Student addManyCompetencesToStudent(Long studentId, List<Long> listOfCompetences) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            List<Competence> competences = new ArrayList<>();
            for (Long id : listOfCompetences) {
                Optional<Competence> competenceOptional = competenceRepository.findById(id);
                competenceOptional.ifPresent(competences::add);
            }
            studentOptional.get().getCompetences().addAll(competences);
            return studentRepository.save(studentOptional.get());
        }
        return null;
    }

    public Student addCompetenceToStudent(Long studentId, Long idCompetence) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Optional<Competence> competenceOptional = competenceRepository.findById(idCompetence);
            competenceOptional.ifPresent(competence -> studentOptional.get().getCompetences().add(competence));
            return studentRepository.save(studentOptional.get());
        }
        return null;
    }

    public Student removeCompetenceById(Long studentId, Long competenceId) {
        Optional<Student> studentFounded = studentRepository.findById(studentId);
        if (studentFounded.isPresent()) {
            studentFounded.get().getCompetences().removeIf(competence -> competence.getId() == competenceId);
            return studentRepository.save(studentFounded.get());
        }
        return null;
    }

    public List<StudentResponse> getAllStudents() {
        List<Student> studentsList = studentRepository.findAll();
        List<StudentResponse> students = new ArrayList<>();
        for (Student studentIndex : studentsList) {
            StudentResponse studentResponse = new StudentResponse(studentIndex);
            students.add(studentResponse);
        }
        return students;
    }

    public Optional<Student> getStudentWithCompetenceById(long studentId) {
        return studentRepository.findById(studentId);
    }

    public List<Student> getAllStudentsWithCompetence() {
        return studentRepository.getAllStudentsCompetences();
    }

    public List<Student> getAllByFilter(StudentFilterParam params) {
        return this.studentRepository.getWithFilter(params);
    }
}