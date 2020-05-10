package com.gmail.yauhen2012.repository;

import java.math.BigDecimal;
import java.util.List;

import com.gmail.yauhen2012.repository.model.ItemDetails;

public interface ItemDetailsRepository {

    List<ItemDetails> findItemWithPriceRange(BigDecimal startPrice, BigDecimal endPrice);

}
