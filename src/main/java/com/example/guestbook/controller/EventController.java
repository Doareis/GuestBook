package com.example.guestbook.controller;

import com.example.guestbook.model.Event;
import com.example.guestbook.model.Guest;
import com.example.guestbook.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value = "/edit/{eventId}")
    public String showEventEditPage(Model model, @PathVariable long eventId) {
        model.addAttribute("guest", new Guest());
        model.addAttribute("event", eventRepository.findOne(eventId));
        return "event";
    }

    @RequestMapping(value = "/add/guest/{eventId}")
    public String addGuest(Model model, @PathVariable Long eventId,  Guest guest) {
        Event event = eventRepository.findOne(eventId);
        guest.setEvent(event);
        event.getGuests().add(guest);
        eventRepository.save(event);

        model.addAttribute("event", event);
        model.addAttribute("guest", new Guest());

        return "event";
    }

}
