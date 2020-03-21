package com.gmail.yauhen2012.service;

import java.util.List;

import com.gmail.yauhen2012.service.model.AddItemDTO;
import com.gmail.yauhen2012.service.model.ItemDTO;

public interface ItemService {

    void add(AddItemDTO addItemDTO);

    ItemDTO findItemById(Long id);

    List<ItemDTO> findAll();

    int deleteItemById(Long id);

}
