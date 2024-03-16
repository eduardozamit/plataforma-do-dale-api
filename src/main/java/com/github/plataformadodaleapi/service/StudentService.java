package com.github.plataformadodaleapi.service;

import com.github.plataformadodaleapi.dto.request.StudentRequestDTO;
import com.github.plataformadodaleapi.dto.response.StudentResponse;
import com.github.plataformadodaleapi.entity.Competence;
import com.github.plataformadodaleapi.entity.GCTrail;
import com.github.plataformadodaleapi.entity.Student;
import com.github.plataformadodaleapi.repository.CompetenceRepository;
import com.github.plataformadodaleapi.repository.StudentCustomRepository;
import com.github.plataformadodaleapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    private StudentRepository studentRepository;
    private CompetenceRepository competenceRepository;
    private StudentCustomRepository studentCustomRepository;

    public StudentService(StudentRepository studentRepository, CompetenceRepository competenceRepository, StudentCustomRepository studentCustomRepository) {
        this.studentRepository = studentRepository;
        this.competenceRepository = competenceRepository;
        this.studentCustomRepository = studentCustomRepository;
    }

    public Student saveStudent(StudentRequestDTO studentRequestDTO) {
        Student student = new Student(studentRequestDTO);
        return studentRepository.save(student);
    }

    public List<StudentResponse> findAllStudents() {
        List<Student> studentsList = studentRepository.findAll();
        List<StudentResponse> students = new ArrayList<>();
        for (Student studentIndex : studentsList) {
            StudentResponse student = new StudentResponse(studentIndex);
            students.add(student);
        }
        return students;
    }

    public Student addCompetenceToStudent(String studentId, String idCompetence) {
        Optional<Student> student = studentRepository.findById(Long.parseLong(studentId));
        if (student.isPresent()) {
            Optional<Competence> competenceOptional = competenceRepository.findById(Long.parseLong(idCompetence));
            competenceOptional.ifPresent(competence -> student.get().getCompetences().add(competence));
            return studentRepository.save(student.get());
        }
        return null;
    }

    public Optional<Student> addManyCompetencesToStudent(String studentId, List<String> listOfCompetences) {
        Optional<Student> student = studentRepository.findById(Long.parseLong(studentId));
        if (student.isPresent()) {
            List<Competence> competences = new ArrayList<>();
            for (String id : listOfCompetences) {
                Optional<Competence> competence = competenceRepository.findById(Long.parseLong(id));
                if (competence.isPresent()) {
                    competences.add(competence.get());
                }
            }
            student.get().getCompetences().addAll(competences);
            return student;
        }
        return Optional.empty();
    }

    public Optional<Student> listStudentWithCompetenceById(long studentId) {
        return studentRepository.listStudentCompetenceById(studentId);
    }

    public List<Student> listStudentsByFilter(String name, Integer age, GCTrail gcTrail) {
        return studentCustomRepository.findCustom(name, age, gcTrail);
    }
}