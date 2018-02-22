package com.example.guestbook.controller;

import com.example.guestbook.model.Event;
import com.example.guestbook.model.Guest;
import com.example.guestbook.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestRepository repository;

    @RequestMapping("remove/{guestId}")
    public String removeGuest(Model model, @PathVariable Long guestId) {
        Event event = repository.findOne(guestId).getEvent();
        repository.delete(guestId);

        model.addAttribute("guest", new Guest());
        model.addAttribute("event", event);
        return "/event";
    }

}
