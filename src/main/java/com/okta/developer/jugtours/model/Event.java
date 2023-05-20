package com.okta.developer.jugtours.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private Long id;
    private Instant date;
    private String title;
    private String description;

    @ManyToMany
    private Set<User> attendees;

    public Event() {
    }

    public Event(Instant date, String title, String description) {
        this.date = date;
        this.title = title;
        this.description = description;
    }

    // getters, setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<User> attendees) {
        this.attendees = attendees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return Objects.equals(getId(), event.getId()) &&
                Objects.equals(getDate(), event.getDate()) &&
                Objects.equals(getTitle(), event.getTitle()) &&
                Objects.equals(getDescription(), event.getDescription()) &&
                Objects.equals(getAttendees(), event.getAttendees());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getDate(),
                getTitle(),
                getDescription(),
                getAttendees());
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", attendees=" + attendees +
                '}';
    }
}
