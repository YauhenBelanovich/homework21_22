package com.gmail.yauhen2012.repository.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;

import com.gmail.yauhen2012.repository.ItemDetailsRepository;
import com.gmail.yauhen2012.repository.model.ItemDetails;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDetailsRepositoryImpl extends GenericDAOImpl<Long, ItemDetails> implements ItemDetailsRepository {

    @Override
    @SuppressWarnings("unchecked")
    public List<ItemDetails> findItemWithPriceRange(BigDecimal startPrice, BigDecimal endPrice) {
        if (startPrice != null && endPrice != null) {
            String hql = "FROM " + entityClass.getSimpleName() +
                    " where price >=:startPrice and price <=:endPrice";
            Query query = entityManager.createQuery(hql);
            query.setParameter("startPrice", startPrice);
            query.setParameter("endPrice", endPrice);
            return query.getResultList();
        }
        if (startPrice == null) {
            String hql = "FROM " + entityClass.getSimpleName() +
                    " where price <=:endPrice";
            Query query = entityManager.createQuery(hql);
            query.setParameter("endPrice", endPrice);
            return query.getResultList();
        } else {
            String hql = "FROM " + entityClass.getSimpleName() +
                    " where price >=:startPrice";
            Query query = entityManager.createQuery(hql);
            query.setParameter("startPrice", startPrice);
            return query.getResultList();
        }
    }

}
