package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.model.skills.HardSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardSkillRepository extends JpaRepository<HardSkill, Long> {
}