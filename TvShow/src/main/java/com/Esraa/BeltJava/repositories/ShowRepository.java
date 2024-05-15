package com.Esraa.BeltJava.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Esraa.BeltJava.models.Show;
import com.Esraa.BeltJava.models.User;

public interface ShowRepository extends CrudRepository<Show, Long> {

    // this method retrieves all the books from the database
    List<Show> findAll();

    // List<Show> findByTasksContaining(User user);

    // List<Show> findByRatingsContaining(User user);

    // List<Show> findByMembersNotContaining(User user);

    List<Show> findByUser(User user);

    List<Show> findByTitle(String title);

    // long countByRatings(User user);

}
