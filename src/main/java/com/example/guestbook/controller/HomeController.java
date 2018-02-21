package com.example.guestbook.controller;

import com.example.guestbook.model.User;
import com.example.guestbook.repository.EventRepository;
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
    public String home(Model model, User user) {
        model.addAttribute("events", repository.findAll());
        //model.addAttribute("events", repository.findByUser(user));
        return "home";
    }

}
