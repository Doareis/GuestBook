package com.example.guestbook.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by douglas.reis on 07/11/17.
 */
@Controller
public class HomeController {

    @RequestMapping(name = "/home")
    public String home(Model model, Authentication auth) {
        model.addAttribute("username", auth.getName());
        return "home";
    }

}
