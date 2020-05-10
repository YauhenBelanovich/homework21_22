package com.gmail.yauhen2012.springbootmodule.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;
import javax.validation.Valid;

import com.gmail.yauhen2012.service.ShopService;
import com.gmail.yauhen2012.service.model.AddShopDTO;
import com.gmail.yauhen2012.service.model.ShopDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shops")
public class ShopController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public String getShopList(@RequestParam(value = "page", defaultValue = "1") String page, Model model) {
        List<ShopDTO> shopDTOList = shopService.getShopsByPage(page);
        model.addAttribute("shopList", shopDTOList);
        logger.debug("Get shopList method");
        return "shops";
    }

    @GetMapping("/add")
    public String addShopPage(Model model) {
        model.addAttribute("shop", new AddShopDTO());
        logger.debug("Get addShopPage method");
        return "shop_add";
    }

    @PostMapping("/add")
    public String addShop(@Valid @ModelAttribute(name = "shop") AddShopDTO shop, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("shop", shop);
            return "shop_add";
        } else {
            shopService.add(shop);
            logger.debug("Post addShop method");
            return "redirect:/shops";
        }
    }

    @GetMapping("/search")
    public String searchShop(@RequestParam(value = "location") String location, Model model) {
        List<ShopDTO> shopsDTOByLocation = shopService.findByLocation(location);
        model.addAttribute("shopsDTOByLocation", shopsDTOByLocation);
        logger.debug("Get shopsByLocation method");
        return "shops";
    }

}
