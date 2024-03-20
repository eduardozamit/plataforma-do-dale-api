package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.model.recruiter.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
}
