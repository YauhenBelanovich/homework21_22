package com.gmail.yauhen2012.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.gmail.yauhen2012.repository.ItemRepository;
import com.gmail.yauhen2012.repository.model.Item;
import com.gmail.yauhen2012.repository.model.ItemDetails;
import com.gmail.yauhen2012.service.ItemService;
import com.gmail.yauhen2012.service.model.AddItemDTO;
import com.gmail.yauhen2012.service.model.ItemDTO;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;}

    @Override
    @Transactional
    public void add(AddItemDTO addItemDTO) {
        Item item = convertItemDTOToDatabaseItem(addItemDTO);
        itemRepository.add(item);
    }

    @Override
    public ItemDTO findItemById(Long id) {
        return null;
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
    public int deleteItemById(Long id) {
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
        return itemDTO;
    }
}
