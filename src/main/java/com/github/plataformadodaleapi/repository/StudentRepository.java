package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.model.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {
    @Query("SELECT s FROM Student s JOIN FETCH s.competences")
    List<Student> getAllStudentsCompetences();
}
