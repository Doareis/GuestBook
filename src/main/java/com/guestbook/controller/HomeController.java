package com.guestbook.controller;

import com.guestbook.model.User;
import com.guestbook.repository.EventRepository;
import com.guestbook.repository.UserRepository;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by douglas.reis on 07/11/17.
 */
@Controller
public class HomeController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(name = "/home")
    public String home(Model model, Principal principal) {
        User user = repository.findByUsername(principal.getName()).get();
        model.addAttribute("user", user);
        return "home";
    }

}
