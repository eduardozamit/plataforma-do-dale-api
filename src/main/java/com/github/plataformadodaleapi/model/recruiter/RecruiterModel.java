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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "recruiter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecruiterModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "company")
    private String company;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @JsonManagedReference
    @Cascade(value = CascadeType.ALL)
    @ManyToMany
    @JoinTable(name = "recruiter_student", joinColumns = @JoinColumn(name = "recruiter_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> favoriteStudents;

    public RecruiterModel(RegisterRecruiterDTO dto) {
        this.name = dto.name();
        this.company = dto.company();
        this.email = dto.email();
        this.password = dto.password();
    }

    public void updateInformation(UpdateRecruiterDTO data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.company() != null) {
            this.company = data.company();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_RECRUITER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}