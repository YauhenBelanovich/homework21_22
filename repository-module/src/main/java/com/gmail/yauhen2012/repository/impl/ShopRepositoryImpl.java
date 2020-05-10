package com.gmail.yauhen2012.repository.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.gmail.yauhen2012.repository.ShopRepository;
import com.gmail.yauhen2012.repository.model.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class ShopRepositoryImpl extends GenericDAOImpl<Long, Shop> implements ShopRepository {

    @Override
    public List<Shop> findByLocation(String location) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> query = cb.createQuery(Shop.class);
        Root<Shop> root = query.from(Shop.class);
        ParameterExpression<String> parameterExpression = cb.parameter(String.class);

        query.select(root).where(cb.equal(root.get("location"), parameterExpression));

        TypedQuery<Shop> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(parameterExpression, location);
        return typedQuery.getResultList();
    }

}
