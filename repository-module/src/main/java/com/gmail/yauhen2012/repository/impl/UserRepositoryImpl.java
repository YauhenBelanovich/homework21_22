package com.gmail.yauhen2012.repository.impl;

import javax.persistence.Query;

import com.gmail.yauhen2012.repository.UserRepository;
import com.gmail.yauhen2012.repository.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends GenericDAOImpl<Long, User> implements UserRepository {

    @Override
    public User findByName(String username) {
        String queryString = "FROM " + entityClass.getSimpleName() + " AS u" +
                " WHERE u.username=:username";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

}
