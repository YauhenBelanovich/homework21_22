package com.gmail.yauhen2012.repository.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.gmail.yauhen2012.repository.GenericDAO;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDAOImpl<I, T> implements GenericDAO<I, T> {

    protected Class<T> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public GenericDAOImpl() {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperClass.getActualTypeArguments()[1];
    }

    @Override
    public void add(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public List<T> findAll() {
        String query = "from " + entityClass.getName() + " c";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }

    @Override
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public T findById(I id) {
        return entityManager.find(entityClass, id);
    }

}
