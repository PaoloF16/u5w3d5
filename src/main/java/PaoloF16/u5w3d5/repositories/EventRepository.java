package PaoloF16.u5w3d5.repositories;

import PaoloF16.u5w3d5.entities.Event;
import PaoloF16.u5w3d5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


    List<Event> findByCreator(User creator);
}