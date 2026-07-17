package PaoloF16.u5w3d5.controllers;


import PaoloF16.u5w3d5.entities.Event;
import PaoloF16.u5w3d5.entities.User;
import PaoloF16.u5w3d5.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")

public class EventController {
    @Autowired
    private EventService eventService;

    //tutti i loggati
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{eventId}")
    public Event getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId);
    }


    //solo organizer

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public Event createEvent(@RequestBody @Validated EventDTO dto,
                             BindingResult validationResult, @AuthenticationPrincipal User currentUser) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(field -> field.getDefaultMessage()).toList());
        }
        return eventService.createEvent(dto, currentUser.getId());
    }

    @PutMapping("/{eventId}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public Event updateEvent(@PathVariable Long eventId, @RequestBody @Validated EventDTO dto,
                             BindingResult validationResult, @AuthenticationPrincipal User currentUser) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(field -> field.getDefaultMessage()).toList());
        }
        return eventService.updateEvent(eventId, dto, currentUser.getUsername());
    }

}