package com.github.plataformadodaleapi.model.recruiter;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.plataformadodaleapi.model.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Table(name = "recruiter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonManagedReference
    @Cascade(value = CascadeType.ALL)
    @ManyToMany
    @JoinTable(name = "recruiter_student", joinColumns = @JoinColumn(name = "recruiter_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> favoriteStudents;

    public Recruiter(RecruiterRequestDTO dto) {
        this.name = dto.name();
    }
}
