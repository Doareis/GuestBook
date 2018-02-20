package com.example.guestbook.controller;

import com.example.guestbook.dto.UserDTO;
import com.example.guestbook.exception.EmailExistsException;
import com.example.guestbook.model.User;
import com.example.guestbook.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@SuppressWarnings("unused")
@Controller
public class RegistrationController {

    @Autowired
    private UserRegistrationService service;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @RequestMapping(value = "/registerUser")
    public String registerUser(@Valid UserDTO user, BindingResult result) {
        User registeredUser = null;
        if (!result.hasErrors()) {
            registeredUser = createUserAccount(user, result);
        }

        if (registeredUser == null) {
            result.rejectValue("email", "message.regError");
        }

        return "/login";
    }

    private User createUserAccount(UserDTO user, BindingResult result) {
        User registeredUser;
        try {
            registeredUser = service.registerNewUserAccount(user);
        } catch (EmailExistsException e) {
            return null;
        }

        return registeredUser;
    }
}
