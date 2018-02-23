package com.guestbook.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by douglas.reis on 16/11/17.
 */
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JoinColumn(name = "guest_id")
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Guest> guests;

    @Column(name = "event_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "location")
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Guest> getGuests() {
        if (guests == null) {
            guests = new ArrayList<>();
        }
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
