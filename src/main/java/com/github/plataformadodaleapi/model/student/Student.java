package com.github.plataformadodaleapi.model.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.plataformadodaleapi.model.recruiter.Recruiter;
import com.github.plataformadodaleapi.model.skills.HardSkill;
import com.github.plataformadodaleapi.model.skills.SoftSkill;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Table(name = "student")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age", length = 3, nullable = false)
    private int age;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "biography")
    private String biography;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "linkedin")
    private String linkedin;
    @Column(name = "profile_picture", length = 1000)
    private String profilePicture;
    @Column(name = "course")
    private String course;
    @Column(name = "course_institution")
    private String courseInstitution;
    @Column(name = "year_of_course_completion")
    private String yearOfCourseCompletion;
    @Column(name = "education_level", length = 24, nullable = false)
    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;
    @Column(name = "gc_trail", length = 54, nullable = false)
    @Enumerated(EnumType.STRING)
    private GCTrail gcTrail;

    @JsonManagedReference
    @Cascade(value = CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "student_hard_skill",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "hard_skill_id")
    )
    private List<HardSkill> hardSkills;
    @JsonManagedReference
    @Cascade(value = CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "student_soft_skill",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "soft_skill_id")
    )
    private List<SoftSkill> softSkills;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

    public Student(StudentRequestDTO studentRequestDTO) {
        this.name = studentRequestDTO.name();
        this.age = studentRequestDTO.age();
        this.email = studentRequestDTO.email();
        this.biography = studentRequestDTO.biography();
        this.city = studentRequestDTO.city();
        this.linkedin = studentRequestDTO.linkedin();
        this.profilePicture = studentRequestDTO.profilePicture();
        this.course = studentRequestDTO.course();
        this.courseInstitution = studentRequestDTO.courseInstitution();
        this.yearOfCourseCompletion = studentRequestDTO.yearOfCourseCompletion();
        this.educationLevel = studentRequestDTO.educationLevel();
        this.gcTrail = studentRequestDTO.gcTrail();
    }
}