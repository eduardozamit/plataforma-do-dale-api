package com.github.plataformadodaleapi.service;

import com.github.plataformadodaleapi.model.skills.HardSkill;
import com.github.plataformadodaleapi.model.skills.SoftSkill;
import com.github.plataformadodaleapi.repository.HardSkillRepository;
import com.github.plataformadodaleapi.repository.SoftSkillRepository;
import com.github.plataformadodaleapi.model.skills.SkillRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    private final HardSkillRepository hardSkillRepository;
    private final SoftSkillRepository softSkillRepository;

    public SkillService(HardSkillRepository hardSkillRepository, SoftSkillRepository softSkillRepository) {
        this.hardSkillRepository = hardSkillRepository;
        this.softSkillRepository = softSkillRepository;
    }

    public HardSkill saveHardSkill(SkillRequestDTO skillRequestDTO) {
        return hardSkillRepository.save(new HardSkill(skillRequestDTO));
    }

    public SoftSkill saveSoftSkill(SkillRequestDTO skillRequestDTO) {
        return softSkillRepository.save(new SoftSkill(skillRequestDTO));
    }

    public HardSkill findHardSkillById(Long id) {
        return hardSkillRepository.findById(id).orElse(null);
    }

    public SoftSkill findSoftSkillById(Long id) {
        return softSkillRepository.findById(id).orElse(null);
    }

    public List<HardSkill> findAllHardSkills() {
        return hardSkillRepository.findAll();
    }

    public List<SoftSkill> findAllSoftSkills() {
        return softSkillRepository.findAll();
    }

    public void deleteHardSkillById(Long id) {
        hardSkillRepository.deleteById(id);
    }

    public void deleteSoftSkillById(Long id) {
        softSkillRepository.deleteById(id);
    }
}