package com.gmail.yauhen2012.service;

import java.math.BigDecimal;
import java.util.List;

import com.gmail.yauhen2012.service.model.AddItemDTO;
import com.gmail.yauhen2012.service.model.ItemDTO;

public interface ItemService {

    void add(AddItemDTO addItemDTO);

    ItemDTO findItemById(Long id);

    int deleteItemById(Long id);

    List<ItemDTO> getItemsByPage(String page);

    List<ItemDTO> findItemByName(String name);

    List<ItemDTO> findItemsWithPriceRange(BigDecimal startPrice, BigDecimal endPrice);

}
