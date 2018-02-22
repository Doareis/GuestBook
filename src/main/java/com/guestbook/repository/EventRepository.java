package com.guestbook.repository;

import com.guestbook.model.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("from Event e where upper(e.user.username) = upper(?1)")
    List<Event> findAllBy(String username);

}
