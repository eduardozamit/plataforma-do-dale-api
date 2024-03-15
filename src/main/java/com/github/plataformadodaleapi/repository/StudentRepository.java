package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.dto.StudentDTOQuery;
import com.github.plataformadodaleapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query(value = "SELECT " +
            "Student.id," +
            " Student.age," +
            " Student.educationLevel," +
            " Student.gcTrail, " +
            "Competence.description" +
            " FROM Student " +
            "JOIN student_competence ON Student.id = student_competence.student_id " +
            "JOIN Competence ON student_competence.competence_id = competence.id " +
            "WHERE student_competence.id = :id", nativeQuery = true)
    List<StudentDTOQuery> findStudentCompetence(@Param("id") long id);

    @Query("SELECT s FROM Student s JOIN FETCH s.competences")
    List<Student> listStudentsCompetences();

    @Query("SELECT s FROM Student s JOIN FETCH s.competences WHERE s.id = :student_id")
    Optional<Student> listStudentCompetenceById(@Param("student_id") long studentId);
}
