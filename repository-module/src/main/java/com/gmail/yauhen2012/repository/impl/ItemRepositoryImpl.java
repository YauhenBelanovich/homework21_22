package com.gmail.yauhen2012.repository.impl;

import java.util.List;
import javax.persistence.Query;

import com.gmail.yauhen2012.repository.ItemRepository;
import com.gmail.yauhen2012.repository.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends GenericDAOImpl<Long, Item> implements ItemRepository {

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> findByName(String itemName) {
        String queryString = "FROM " + entityClass.getSimpleName() + " AS u" +
                " WHERE u.name=:name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", itemName);
        return query.getResultList();
    }

}
