package com.gmail.yauhen2012.springbootmodule.controller;

import java.lang.invoke.MethodHandles;
import javax.validation.Valid;

import com.gmail.yauhen2012.service.UserService;
import com.gmail.yauhen2012.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping
    public String addUserPage(Model model) {
        model.addAttribute("user", new UserDTO());
        logger.debug("Get addUserPage method");
        return "registration";
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute(name = "user") UserDTO user, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            return "registration";
        } else {
            userService.add(user);
            logger.debug("Post addUser method");
            return "redirect:/items";
        }
    }

}
