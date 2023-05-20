package com.okta.developer.jugtours.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_groups")
public class Group {

    @Id
    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_sequence"
    )
    private Long id;
    @NotNull
    private String name;
    private String address;
    private String city;
    private String stateOrProvince;
    private String country;
    private String postalCode;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Event> events;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    // getters, setters, equals, hashcode and toString

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group group)) return false;
        return Objects.equals(getId(), group.getId()) &&
                Objects.equals(getName(), group.getName()) &&
                Objects.equals(getAddress(), group.getAddress()) &&
                Objects.equals(getCity(), group.getCity()) &&
                Objects.equals(getStateOrProvince(), group.getStateOrProvince()) &&
                Objects.equals(getCountry(), group.getCountry()) &&
                Objects.equals(getPostalCode(), group.getPostalCode()) &&
                Objects.equals(getUser(), group.getUser()) &&
                Objects.equals(getEvents(), group.getEvents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getName(),
                getAddress(),
                getCity(),
                getStateOrProvince(),
                getCountry(),
                getPostalCode(),
                getUser(),
                getEvents());
    }
}
