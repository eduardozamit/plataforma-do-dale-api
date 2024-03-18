package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.model.skills.HardSkill;
import com.github.plataformadodaleapi.model.skills.SoftSkill;
import com.github.plataformadodaleapi.model.student.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {

    private EntityManager entityManager;

    public StudentRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> getWithFilter(StudentFilterParam params) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = query.from(Student.class);
        List<Predicate> predicates = new ArrayList<>();

        if (params.getName() != null) {
            predicates.add(criteriaBuilder.like(studentRoot.get("name"), "%" + params.getName() + "%"));
        }

        if (params.getAge() != null && params.getAge() > 0) {
            predicates.add(criteriaBuilder.equal(studentRoot.get("age"), params.getAge()));
        }

        if (params.getCity() != null) {
            predicates.add(criteriaBuilder.equal(studentRoot.get("city"), params.getCity()));
        }

        if (params.getGcTrail() != null) {
            predicates.add(criteriaBuilder.equal(studentRoot.get("gcTrail"), params.getGcTrail()));
        }

        if (params.getEducationLevel() != null) {
            predicates.add(criteriaBuilder.equal(studentRoot.get("educationLevel"), params.getEducationLevel()));
        }

        if (params.getHardSkill() != null && !params.getHardSkill().isEmpty()) {
            Join<Student, HardSkill> join = studentRoot.join("hardSkill");
            predicates.add(join.get("id").in(params.getHardSkill()));
        }

        if (params.getSoftSkill() != null && !params.getSoftSkill().isEmpty()) {
            Join<Student, SoftSkill> join = studentRoot.join("softSkill");
            predicates.add(join.get("id").in(params.getSoftSkill()));
        }

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<Student> queryResult = this.entityManager.createQuery(query);
        return queryResult.getResultList();
    }
}