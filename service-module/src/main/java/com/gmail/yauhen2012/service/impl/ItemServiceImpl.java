package com.gmail.yauhen2012.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.gmail.yauhen2012.repository.ItemDetailsRepository;
import com.gmail.yauhen2012.repository.ItemRepository;
import com.gmail.yauhen2012.repository.ShopRepository;
import com.gmail.yauhen2012.repository.model.Item;
import com.gmail.yauhen2012.repository.model.ItemDetails;
import com.gmail.yauhen2012.repository.model.Shop;
import com.gmail.yauhen2012.service.ItemService;
import com.gmail.yauhen2012.service.constant.PaginationConstant;
import com.gmail.yauhen2012.service.model.AddItemDTO;
import com.gmail.yauhen2012.service.model.AddShopDTO;
import com.gmail.yauhen2012.service.model.ItemDTO;
import com.gmail.yauhen2012.service.model.ShopDTO;
import com.gmail.yauhen2012.service.util.PaginationUtil;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;
    private final ItemDetailsRepository itemDetailsRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ShopRepository shopRepository, ItemDetailsRepository itemDetailsRepository) {
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
        this.itemDetailsRepository = itemDetailsRepository;
    }

    @Override
    @Transactional
    public void add(AddItemDTO addItemDTO) {
        Item item = convertItemDTOToDatabaseItem(addItemDTO);
        itemRepository.add(item);

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setPrice(addItemDTO.getPrice());
        item.setItemDetails(itemDetails);

        itemDetails.setItem(item);
        item.setItemDetails(itemDetails);

        List<Shop> shops = getShops(addItemDTO);
        item.setShops(shops);
    }

    @Override
    @Transactional
    public ItemDTO findItemById(Long id) {
        Item item = itemRepository.findById(id);
        return convertDatabaseObjectToDTO(item);
    }

    @Override
    @Transactional
    public int deleteItemById(Long id) {
        Item item = itemRepository.findById(id);
        itemRepository.remove(item);
        return 0;
    }

    @Override
    @Transactional
    public List<ItemDTO> getItemsByPage(String page) {

        int pageInt = Integer.parseInt(page);
        List<Item> itemList = itemRepository.getObjectsByPage(PaginationUtil.findStartPosition(pageInt), PaginationConstant.ITEMS_BY_PAGE);
        return itemList.stream()
                .map(this::convertDatabaseObjectToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ItemDTO> findItemByName(String name) {
        List<Item> itemList = itemRepository.findByName(name);
        return itemList.stream()
                .map(this::convertDatabaseObjectToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ItemDTO> findItemsWithPriceRange(BigDecimal startPrice, BigDecimal endPrice) {
        List<ItemDetails> itemDetailsList = itemDetailsRepository.findItemWithPriceRange(startPrice, endPrice);
        List<Item> itemList = new ArrayList<>();
        for (ItemDetails details : itemDetailsList) {
            Item item = itemRepository.findById(details.getItemId());
            itemList.add(item);
        }
        return itemList.stream()
                .map(this::convertDatabaseObjectToDTO)
                .collect(Collectors.toList());
    }

    private List<Shop> getShops(AddItemDTO addItemDTO) {
        return addItemDTO.getAddShopDTOS()
                .stream()
                .map(AddShopDTO::getShopId)
                .filter(Objects::nonNull)
                .map(shopRepository::findById)
                .collect(Collectors.toList());
    }

    private Item convertItemDTOToDatabaseItem(AddItemDTO addItemDTO) {
        Item item = new Item();
        item.setName(addItemDTO.getName());
        item.setDescription(addItemDTO.getDescription());
        return item;
    }

    private ItemDTO convertDatabaseObjectToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        if (item.getItemDetails() != null) {
            BigDecimal price = item.getItemDetails().getPrice();
            itemDTO.setPrice(price);
        }
        List<ShopDTO> shopDTOS = item.getShops().stream()
                .map(this::convertDatabaseShopToDTO)
                .collect(Collectors.toList());
        itemDTO.setShopDTOS(shopDTOS);
        return itemDTO;
    }

    private ShopDTO convertDatabaseShopToDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopId(shop.getShopId());
        shopDTO.setName(shop.getName());
        shopDTO.setLocation(shop.getLocation());
        return shopDTO;
    }

}
