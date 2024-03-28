package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.model.recruiter.RecruiterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface RecruiterRepository extends JpaRepository<RecruiterModel, Long> {
    UserDetails findByemail(String username);

    boolean existsByemail(String email);
}
