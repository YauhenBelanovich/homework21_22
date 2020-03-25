package com.gmail.yauhen2012.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.gmail.yauhen2012.repository.ItemRepository;
import com.gmail.yauhen2012.repository.ShopRepository;
import com.gmail.yauhen2012.repository.model.Item;
import com.gmail.yauhen2012.repository.model.ItemDetails;
import com.gmail.yauhen2012.repository.model.Shop;
import com.gmail.yauhen2012.service.ItemService;
import com.gmail.yauhen2012.service.model.AddItemDTO;
import com.gmail.yauhen2012.service.model.AddShopDTO;
import com.gmail.yauhen2012.service.model.ItemDTO;
import com.gmail.yauhen2012.service.model.ShopDTO;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ShopRepository shopRepository) {
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
    }

    @Override
    @Transactional
    public void add(AddItemDTO addItemDTO) {
        Item item = convertItemDTOToDatabaseItem(addItemDTO);
        ItemDetails itemDetails = item.getItemDetails();
        item.setItemDetails(null);
        itemRepository.add(item);
        itemDetails.setItem(item);
        itemDetails.setItemId(item.getId());
        item.setItemDetails(itemDetails);

        List<Shop> shops = addItemDTO.getAddShopDTOS()
                .stream()
                .map(AddShopDTO::getShopId)
                .filter(Objects::nonNull)
                .map(shopRepository::findById)
                .collect(Collectors.toList());

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
    public List<ItemDTO> findAll() {
        List<Item> itemList = itemRepository.findAll();
        List<ItemDTO> itemDTOList = itemList.stream()
                .map(this::convertDatabaseObjectToDTO)
                .collect(Collectors.toList());
        return itemDTOList;
    }

    @Override
    @Transactional
    public int deleteItemById(Long id) {
        Item item = itemRepository.findById(id);
        itemRepository.remove(item);
        return 0;
    }

    private Item convertItemDTOToDatabaseItem(AddItemDTO addItemDTO) {
        Item item = new Item();
        item.setName(addItemDTO.getName());
        item.setDescription(addItemDTO.getDescription());

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setPrice(addItemDTO.getPrice().toString());

        item.setItemDetails(itemDetails);
        return item;
    }

    private ItemDTO convertDatabaseObjectToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        if (item.getItemDetails() != null) {
            String priceFromDatabase = item.getItemDetails().getPrice();
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceFromDatabase));
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
