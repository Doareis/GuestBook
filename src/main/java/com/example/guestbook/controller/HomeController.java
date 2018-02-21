package com.example.guestbook.controller;

import com.example.guestbook.model.User;
import com.example.guestbook.repository.EventRepository;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by douglas.reis on 07/11/17.
 */
@Controller
public class HomeController {

    @Autowired
    private EventRepository repository;

    @RequestMapping(name = "/home")
    public String home(Model model, Principal principal) {
        model.addAttribute("events", repository.findAllBy(principal.getName()));
        //model.addAttribute("events", repository.findAll());
        return "home";
    }

}
