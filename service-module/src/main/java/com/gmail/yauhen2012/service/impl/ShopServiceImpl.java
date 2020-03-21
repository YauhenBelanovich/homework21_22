package com.gmail.yauhen2012.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.gmail.yauhen2012.repository.ShopRepository;
import com.gmail.yauhen2012.repository.model.Shop;
import com.gmail.yauhen2012.service.ShopService;
import com.gmail.yauhen2012.service.model.AddShopDTO;
import com.gmail.yauhen2012.service.model.ShopDTO;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {this.shopRepository = shopRepository;}

    @Override
    @Transactional
    public List<ShopDTO> findAll() {
        List<Shop> shopList = shopRepository.findAll();
        List<ShopDTO> shopDTOList = shopList.stream()
                .map(this::convertDatabaseObjectToDTO)
                .collect(Collectors.toList());
        return shopDTOList;
    }

    @Override
    @Transactional
    public void add(AddShopDTO addShopDTO) {
        Shop shop = convertItemDTOToDatabaseItem(addShopDTO);
        shopRepository.add(shop);
    }

    private Shop convertItemDTOToDatabaseItem(AddShopDTO addShopDTO) {
        Shop shop = new Shop();
        shop.setName(addShopDTO.getName());
        shop.setLocation(addShopDTO.getLocation());
        return shop;
    }

    private ShopDTO convertDatabaseObjectToDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopId(shop.getShopId());
        shopDTO.setName(shop.getName());
        shopDTO.setLocation(shop.getLocation());
        return shopDTO;
    }

}
