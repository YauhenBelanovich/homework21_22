package com.gmail.yauhen2012.repository;

import java.util.List;

import com.gmail.yauhen2012.repository.model.Item;

public interface ItemRepository extends GenericDAO<Long, Item> {

    List<Item> findByName(String itemName);

}

