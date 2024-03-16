package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> , StudentRepositoryCustom{
    @Query("SELECT s FROM Student s JOIN FETCH s.competences")
    List<Student> listAllStudentsCompetences();

    @Query("SELECT s FROM Student s JOIN FETCH s.competences WHERE s.id = :student_id")
    Optional<Student> listStudentCompetenceById(@Param("student_id") long studentId);
}
