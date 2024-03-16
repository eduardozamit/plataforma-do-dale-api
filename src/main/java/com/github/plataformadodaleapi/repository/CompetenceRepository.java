package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.model.student.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {

}
