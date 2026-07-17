package PaoloF16.u5w3d5.services;

import PaoloF16.u5w3d5.entities.Event;
import PaoloF16.u5w3d5.entities.User;
import PaoloF16.u5w3d5.exceptions.BadRequestException;
import PaoloF16.u5w3d5.exceptions.NotFoundException;
import PaoloF16.u5w3d5.payloads.EventDTO;
import PaoloF16.u5w3d5.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;

    public Event createEvent(EventDTO dto, Long creatorId) {
        User creator = userService.findById(creatorId);

        if (!creator.getRole().name().equals("ORGANIZER")) {
            throw new BadRequestException("Only organizers can create events");
        }

        Event event = new Event(
                dto.title(), dto.description(), dto.date(), dto.location(), dto.totalSeats(),
                dto.totalSeats(), creator
        );

        return eventRepository.save(event);
    }


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByCreator(String username) {
        User creator = userService.findByUsername(username);
        return eventRepository.findByCreator(creator);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
    }

    public Event updateEvent(Long id, EventDTO dto, String username) {
        Event existing = getEventById(id);
        if (!existing.getCreator().getUsername().equals(username)) {
            throw new BadRequestException("You can only update your own events");
        }
        existing.setTitle(dto.title());
        existing.setDescription(dto.description());
        existing.setDate(dto.date());
        existing.setLocation(dto.location());
        existing.setTotalSeats(dto.totalSeats());

        return eventRepository.save(existing);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id, String username) {
        Event existing = getEventById(id);
        if (!existing.getCreator().getUsername().equals(username)) {
            throw new BadRequestException("You can only delete your own events");
        }
        eventRepository.delete(existing);
    }


}
