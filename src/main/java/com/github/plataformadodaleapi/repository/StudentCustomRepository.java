package com.github.plataformadodaleapi.repository;

import com.github.plataformadodaleapi.entity.GCTrail;
import com.github.plataformadodaleapi.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentCustomRepository {
    @PersistenceContext
    EntityManager em;

    public StudentCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Student> findCustom(String name, Integer age, GCTrail gcTrail) {
        StringBuilder queryBuilder = new StringBuilder("Select s from Student as s ");
        String condicao = "where";

        if (name != null) {
            queryBuilder.append(condicao).append(" s.name = :name");
            condicao = " and";
        }

        if (age != null && age > 0) {
            queryBuilder.append(condicao).append(" s.age = :age");
            condicao = " and";
        }

        if (gcTrail != null) {
            queryBuilder.append(condicao).append(" s.gcTrail = :gcTrail");
        }
        var query = em.createQuery(queryBuilder.toString(), Student.class);

        if (name != null) {
            query.setParameter("name", name);
        }

        if (age != null && age > 0) {
            query.setParameter("age", age);
        }
        if (gcTrail != null) {
            query.setParameter("gcTrail", gcTrail);
        }

        return query.getResultList();
    }
}