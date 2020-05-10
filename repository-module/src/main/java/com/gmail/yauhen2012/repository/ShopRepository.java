package com.gmail.yauhen2012.repository;

import java.util.List;

import com.gmail.yauhen2012.repository.model.Shop;

public interface ShopRepository extends GenericDAO<Long, Shop> {

    List<Shop> findByLocation(String location);

}
