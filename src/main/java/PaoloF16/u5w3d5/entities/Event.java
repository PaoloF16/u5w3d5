package PaoloF16.u5w3d5.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String location;

    @Column(name = "total_seats", nullable = false)
    private int totalSeats;

    @Column(name = "available_seats", nullable = false)
    private int availableSeats;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    public Event(String title, String description, LocalDate date, String location, int totalSeats, int availableSeats, User creator) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.creator = creator;


    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                ", creator=" + creator +
                '}';
    }
}