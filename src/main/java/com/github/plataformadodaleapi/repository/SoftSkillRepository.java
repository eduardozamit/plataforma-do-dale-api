package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.model.skills.SoftSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftSkillRepository extends JpaRepository<SoftSkill, Long> {
}
