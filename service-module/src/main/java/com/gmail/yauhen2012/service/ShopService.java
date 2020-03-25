package com.gmail.yauhen2012.service;

import java.util.List;

import com.gmail.yauhen2012.service.model.AddShopDTO;
import com.gmail.yauhen2012.service.model.ShopDTO;

public interface ShopService {

    List<ShopDTO> findAll();

    void add(AddShopDTO addShopDTO);

}
