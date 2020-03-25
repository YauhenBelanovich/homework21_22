package com.gmail.yauhen2012.repository.impl;

import com.gmail.yauhen2012.repository.ItemRepository;
import com.gmail.yauhen2012.repository.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends GenericDAOImpl<Long, Item> implements ItemRepository {

}
