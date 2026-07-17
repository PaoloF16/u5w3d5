package PaoloF16.u5w3d5.repositories;

import PaoloF16.u5w3d5.entities.Booking;
import PaoloF16.u5w3d5.entities.Event;
import PaoloF16.u5w3d5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByEvent(Event event);

    Optional<Booking> findByUserAndEvent(User user, Event event);
}
