package com.example.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by douglasreis on 07/12/17.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showLoginForm(){
        return "login";
    }

}
