package com.example.guestbook.repository;

import com.example.guestbook.model.Event;
import com.example.guestbook.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("from Event e where e.user = ?1")
    List<Event> findByUser(User user);

}
