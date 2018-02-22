package com.guestbook.controller;

import com.guestbook.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by douglas.reis on 07/12/17.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

}
