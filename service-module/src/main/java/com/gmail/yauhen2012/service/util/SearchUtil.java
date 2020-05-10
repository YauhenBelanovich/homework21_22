package com.gmail.yauhen2012.service.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.gmail.yauhen2012.service.ItemService;
import com.gmail.yauhen2012.service.model.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class SearchUtil {

    private static ItemService itemService;

    public SearchUtil(ItemService itemService) {SearchUtil.itemService = itemService;}

    public static List<ItemDTO> searchHandling(BigDecimal startPrice, BigDecimal endPrice, String name) {
        List<ItemDTO> itemDTOList = new ArrayList<>();
        if (name.equals("")) {
            itemDTOList = itemService.findItemsWithPriceRange(startPrice, endPrice);
        }
        if (startPrice == null && endPrice == null) {
            itemDTOList = itemService.findItemByName(name);
        }
        if (!name.equals("") && startPrice != null || endPrice != null) {
            List<ItemDTO> itemDTOS = itemService.findItemsWithPriceRange(startPrice, endPrice);
            for (ItemDTO itemDTO : itemDTOS) {
                if (itemDTO.getName().equals(name)) {
                    itemDTOList.add(itemDTO);
                }
            }
        }
        return itemDTOList;
    }

}
