package com.github.plataformadodaleapi.controller;

import com.github.plataformadodaleapi.model.skills.HardSkill;
import com.github.plataformadodaleapi.model.skills.SoftSkill;
import com.github.plataformadodaleapi.service.SkillService;
import com.github.plataformadodaleapi.model.skills.SkillRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillsController {
    private final SkillService service;

    public SkillsController(SkillService service) {
        this.service = service;
    }

    @GetMapping("/hard")
    public ResponseEntity<List<HardSkill>> findAllHardSkills() {
        List<HardSkill> hardSkills = service.findAllHardSkills();
        return ResponseEntity.ok().body(hardSkills);
    }

    @GetMapping("/hard/{id}")
    public ResponseEntity<HardSkill> findHardSkillById(@PathVariable Long id) {
        HardSkill hardSkill = service.findHardSkillById(id);
        if (hardSkill != null) {
            return ResponseEntity.ok().body(hardSkill);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/hard/save")
    public ResponseEntity<HardSkill> saveHardSkill(@RequestBody SkillRequestDTO skillRequestDTO) {
        HardSkill savedHardSkill = service.saveHardSkill(skillRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHardSkill);
    }

    @DeleteMapping("/hard/delete/{id}")
    public ResponseEntity<Void> deleteHardSkillById(@PathVariable Long id) {
        service.deleteHardSkillById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/soft")
    public ResponseEntity<List<SoftSkill>> findAllSoftSkills() {
        List<SoftSkill> softSkills = service.findAllSoftSkills();
        return ResponseEntity.ok().body(softSkills);
    }

    @GetMapping("/soft/{id}")
    public ResponseEntity<SoftSkill> findSoftSkillById(@PathVariable Long id) {
        SoftSkill softSkill = service.findSoftSkillById(id);
        if (softSkill != null) {
            return ResponseEntity.ok().body(softSkill);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/soft/save")
    public ResponseEntity<SoftSkill> saveSoftSkill(@RequestBody SkillRequestDTO skillRequestDTO) {
        SoftSkill savedSoftSkill = service.saveSoftSkill(skillRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSoftSkill);
    }

    @DeleteMapping("/soft/delete/{id}")
    public ResponseEntity<Void> deleteSoftSkillById(@PathVariable Long id) {
        service.deleteSoftSkillById(id);
        return ResponseEntity.noContent().build();
    }
}