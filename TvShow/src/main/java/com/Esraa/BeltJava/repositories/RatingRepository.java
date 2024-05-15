package com.Esraa.BeltJava.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Esraa.BeltJava.models.Rating;
import com.Esraa.BeltJava.models.Show;

public interface RatingRepository extends CrudRepository<Rating, Long> {

    // this method retrieves all the books from the database
    List<Rating> findAll();

    List<Rating> findAllByOrderByRankingAsc();

    List<Rating> findByShow(Show show);

    List<Rating> findByShowOrderByRankingAsc(Show show);

    List<Rating> findByShowOrderByRankingDesc(Show show);
    // List<Rating> findByShowTopOrderByRankingAsc(Show show);
}
