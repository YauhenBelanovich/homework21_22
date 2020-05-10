package com.gmail.yauhen2012.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.gmail.yauhen2012.repository.ShopRepository;
import com.gmail.yauhen2012.repository.model.Shop;
import com.gmail.yauhen2012.service.ShopService;
import com.gmail.yauhen2012.service.constant.PaginationConstant;
import com.gmail.yauhen2012.service.model.AddShopDTO;
import com.gmail.yauhen2012.service.model.ShopDTO;
import com.gmail.yauhen2012.service.util.PaginationUtil;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {this.shopRepository = shopRepository;}

    @Override
    @Transactional
    public List<ShopDTO> findAll() {
        List<Shop> shopList = shopRepository.findAll();
        return shopList.stream()
                .map(this::convertDatabaseObjectToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ShopDTO> getShopsByPage(String page) {

        int pageInt = Integer.parseInt(page);
        List<Shop> shopList = shopRepository.getObjectsByPage(
                PaginationUtil.findStartPosition(pageInt),
                PaginationConstant.ITEMS_BY_PAGE
        );
        return shopList.stream()
                .map(this::convertDatabaseObjectToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void add(AddShopDTO addShopDTO) {
        Shop shop = convertShopDTOToDatabaseShop(addShopDTO);
        shopRepository.add(shop);
    }

    @Override
    @Transactional
    public List<ShopDTO> findByLocation(String location) {
        List<Shop> shopFromDB = shopRepository.findByLocation(location);
        return shopFromDB.stream()
                .map(this::convertDatabaseObjectToDTO)
                .collect(Collectors.toList());
    }

    private Shop convertShopDTOToDatabaseShop(AddShopDTO addShopDTO) {
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
