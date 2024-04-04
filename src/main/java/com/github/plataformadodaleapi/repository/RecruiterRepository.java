package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.model.recruiter.RecruiterModel;
import com.github.plataformadodaleapi.model.student.StudentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface RecruiterRepository extends JpaRepository<RecruiterModel, Long> {
    UserDetails findByemail(String username);

    boolean existsByemail(String email);

    @Query(value = "SELECT s.id," +
            "       s.name AS name," +
            "       s.age AS age," +
            "       s.gc_trail AS gcTrail," +
            "       s.linkedin AS linkedin," +
            "       s.email AS email," +
            "       s.biography AS biography," +
            "       s.profile_picture AS profilePicture," +
            "       s.course AS course," +
            "       s.education_level AS educationLevel," +
            "       s.city AS city," +
            "       s.course_institution AS courseInstitution," +
            "       s.year_of_course_completion AS yearOfCourseCompletion," +
            "       CASE WHEN rs.recruiter_id = :recruiter_id THEN true ELSE false END AS favorited," +
            "       GROUP_CONCAT(DISTINCT hs.description SEPARATOR ', ') AS hardSkills," +
            "       GROUP_CONCAT(DISTINCT ss.description SEPARATOR ', ') AS softSkills" +
            " FROM student AS s" +
            " LEFT OUTER JOIN recruiter_student AS rs ON s.id = rs.student_id AND rs.recruiter_id = :recruiter_id" +
            " LEFT OUTER JOIN student_hard_skill AS shs ON s.id = shs.student_id" +
            " LEFT OUTER JOIN hard_skill AS hs ON shs.hard_skill_id = hs.id" +
            " LEFT OUTER JOIN student_soft_skill AS sss ON s.id = sss.student_id" +
            " LEFT OUTER JOIN soft_skill AS ss ON sss.soft_skill_id = ss.id" +
            " GROUP BY s.id;", nativeQuery = true)
    List<StudentProjection> findAllStudents(@Param("recruiter_id") Long recruiterId);

    @Query(value = "SELECT s.id," +
            "       s.name AS name," +
            "       s.age AS age," +
            "       s.gc_trail AS gcTrail," +
            "       s.linkedin AS linkedin," +
            "       s.email AS email," +
            "       s.biography AS biography," +
            "       s.profile_picture AS profilePicture," +
            "       s.course AS course," +
            "       s.education_level AS educationLevel," +
            "       s.city AS city," +
            "       s.course_institution AS courseInstitution," +
            "       s.year_of_course_completion AS yearOfCourseCompletion," +
            "       CASE WHEN rs.recruiter_id = :recruiter_id THEN true ELSE false END AS favorited," +
            "       GROUP_CONCAT(DISTINCT hs.description SEPARATOR ', ') AS hardSkills," +
            "       GROUP_CONCAT(DISTINCT ss.description SEPARATOR ', ') AS softSkills" +
            " FROM student AS s" +
            " LEFT OUTER JOIN recruiter_student AS rs ON s.id = rs.student_id AND rs.recruiter_id = :recruiter_id" +
            " LEFT OUTER JOIN student_hard_skill AS shs ON s.id = shs.student_id" +
            " LEFT OUTER JOIN hard_skill AS hs ON shs.hard_skill_id = hs.id" +
            " LEFT OUTER JOIN student_soft_skill AS sss ON s.id = sss.student_id" +
            " LEFT OUTER JOIN soft_skill AS ss ON sss.soft_skill_id = ss.id" +
            " WHERE rs.recruiter_id = :recruiter_id" +
            " GROUP BY s.id;", nativeQuery = true)
    List<StudentProjection> findFavoritedStudents(@Param("recruiter_id") Long recruiterId);

    @Query(value = "SELECT EXISTS (" +
            "    SELECT 1" +
            "    FROM student" +
            "    WHERE id = :student_id" +
            ") AS existStudentById;", nativeQuery = true)
    Long existStudentById(@Param("student_id") Long studentId);

    @Query(value = "SELECT EXISTS (" +
            "    SELECT 1" +
            "    FROM recruiter_student" +
            "    WHERE recruiter_id = :recruiter_id AND student_id = :student_id" +
            ") AS studentIsFavorited;", nativeQuery = true)
    Long studentIsFavorited(@Param("student_id") Long studentId, @Param("recruiter_id") Long recruiterId);

    @Query(value = "INSERT INTO recruiter_student (recruiter_id, student_id) VALUES (:recruiter_id, :student_id)", nativeQuery = true)
    @Modifying
    void favoriteStudentById(@Param("student_id") Long studentId, @Param("recruiter_id") Long recruiterId);

    @Modifying
    @Query(value = "DELETE FROM recruiter_student WHERE student_id = :student_id AND recruiter_id = :recruiter_id", nativeQuery = true)
    void disfavorStudentById(@Param("student_id") Long studentId, @Param("recruiter_id") Long recruiterId);
}