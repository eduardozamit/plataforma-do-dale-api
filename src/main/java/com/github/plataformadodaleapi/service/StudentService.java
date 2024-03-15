package com.github.plataformadodaleapi.service;

import com.github.plataformadodaleapi.dto.StudentDTOQuery;
import com.github.plataformadodaleapi.dto.request.StudentRequestDTO;
import com.github.plataformadodaleapi.dto.response.StudentCompetenceResponse;
import com.github.plataformadodaleapi.dto.response.StudentResponse;
import com.github.plataformadodaleapi.entity.Competence;
import com.github.plataformadodaleapi.entity.Student;
import com.github.plataformadodaleapi.repository.CompetenceRepository;
import com.github.plataformadodaleapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    private StudentRepository repository;
    private CompetenceRepository competenceRepository;

    public StudentService(StudentRepository repository, CompetenceRepository competenceRepository) {
        this.repository = repository;
        this.competenceRepository = competenceRepository;
    }

    public Student saveStudent(StudentRequestDTO studentRequestDTO) {
        Student student = new Student(studentRequestDTO);
        return repository.save(student);
    }

    public Student saveStudentCompetence(StudentRequestDTO studentRequestDTO, long idCompetence) {
        Student student = new Student(studentRequestDTO);
        return addCompetence(student.getId(), idCompetence);
    }

    public StudentCompetenceResponse findStudentCompetenceById(long id) {
        return repository.listStudentCompetenceById(id);
    }

    public List<StudentResponse> findAll() {
        List<Student> studentsList = repository.findAll();
        List<StudentResponse> students = new ArrayList<>();
        for (Student studentIndex : studentsList) {
            StudentResponse student = new StudentResponse(studentIndex);
            students.add(student);
        }
        return students;
    }

    public List<StudentDTOQuery> findStudentWithCompetenceById(long id) {
        return repository.findStudentCompetence(id);
    }

    public Student addCompetence(long studentId, long idCompetence) {
        Optional<Student> student = repository.findById(studentId);
        Optional<Competence> competence = competenceRepository.findById(idCompetence);
        System.out.println(student.get().toString());
        System.out.println(competence.get().toString());
        if (student.isPresent() && competence.isPresent()) {
            student.get().getCompetences().add(competence.get());
        }
        return repository.save(student.get());
    }
}