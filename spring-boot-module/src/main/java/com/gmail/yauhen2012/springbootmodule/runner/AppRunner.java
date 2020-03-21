package com.gmail.yauhen2012.springbootmodule.runner;

import java.math.BigDecimal;

import com.gmail.yauhen2012.service.ItemService;
import com.gmail.yauhen2012.service.model.AddItemDTO;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    private final ItemService itemService;

    public AppRunner(ItemService itemService) {
        this.itemService = itemService;}

    @Override
    public void run(ApplicationArguments args) throws Exception {

        AddItemDTO addItemDTO = new AddItemDTO();
        addItemDTO.setName("Name1");
        addItemDTO.setDescription("description1");
        addItemDTO.setPrice(BigDecimal.valueOf(15.2));


        itemService.add(addItemDTO);
    }

}
