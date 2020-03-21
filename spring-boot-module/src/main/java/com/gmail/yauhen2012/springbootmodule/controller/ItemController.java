package com.gmail.yauhen2012.springbootmodule.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.validation.Valid;

import com.gmail.yauhen2012.service.ItemService;
import com.gmail.yauhen2012.service.model.AddItemDTO;
import com.gmail.yauhen2012.service.model.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String getItemList(Model model) {
        List<ItemDTO> itemDTOList = itemService.findAll();
        model.addAttribute("itemList", itemDTOList);
        logger.debug("Get ItemList method");
        return "items";
    }

    @GetMapping("/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        ItemDTO item = itemService.findItemById(id);
        model.addAttribute("itemById", item);
        logger.debug("Get itemById method");
        return "item";
    }

    @GetMapping("/add")
    public String addItemPage(Model model) {
        model.addAttribute("item", new AddItemDTO());
        logger.debug("Get addItemPage method");
        return "item_add";
    }

    @PostMapping("/add")
    public String addItem(@Valid @ModelAttribute(name = "item") AddItemDTO item, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("item", item);
            return "item_add";
        } else {
            itemService.add(item);
            logger.debug("Post addItem method");
            return "redirect:/items";
        }
    }

    @DeleteMapping("/{id}/delete")
    public String deleteItemById(@PathVariable Long id, Model model) {
        model.addAttribute("itemById", itemService.deleteItemById(id));
        logger.debug("Get deleteItemById method");
        return "redirect:/items";
    }

}
