package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.entity.Competence;
import com.github.plataformadodaleapi.entity.Student;
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

        if (params.getGcTrail() != null) {
            predicates.add(criteriaBuilder.equal(studentRoot.get("gcTrail"), params.getGcTrail()));
        }

        if (params.getCompetences() != null && !params.getCompetences().isEmpty()) {
            Join<Student, Competence> join = studentRoot.join("competences");
            predicates.add(join.get("id").in(params.getCompetences()));
        }

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<Student> queryResult = this.entityManager.createQuery(query);
        return queryResult.getResultList();
    }

}