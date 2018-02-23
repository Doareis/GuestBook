package com.guestbook.controller;

import com.guestbook.model.Event;
import com.guestbook.model.Guest;
import com.guestbook.model.User;
import com.guestbook.repository.EventRepository;
import com.guestbook.repository.UserRepository;
import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/edit/{eventId}")
    public String showEventEditPage(Model model, @PathVariable long eventId) {
        model.addAttribute("guest", new Guest());
        model.addAttribute("event", eventRepository.findOne(eventId));
        return "event";
    }

    @RequestMapping(value = "/add/guest/{eventId}")
    public String addGuest(Model model, @PathVariable Long eventId, Guest guest) {
        Event event = eventRepository.findOne(eventId);
        guest.setEvent(event);
        event.getGuests().add(guest);
        eventRepository.save(event);

        model.addAttribute("event", event);
        model.addAttribute("guest", new Guest());

        return "event";
    }

    @RequestMapping("/new")
    public String showNewEventPage(Model model) {
        model.addAttribute("event", new Event());
        return "newEvent";
    }

    @RequestMapping("/create")
    public String createEvent(Event event, Principal principal, Model model) {
        Optional<User> optUser = userRepository.findByUsername(principal.getName());

        if (optUser.isPresent()) {
            User user = optUser.get();
            user.addEvent(event);
            userRepository.save(user);
            model.addAttribute("user", user);
        }

        return "/home";
    }

}
