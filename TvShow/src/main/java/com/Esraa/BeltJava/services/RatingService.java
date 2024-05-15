package com.Esraa.BeltJava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Esraa.BeltJava.models.Show;
import com.Esraa.BeltJava.models.Rating;
import com.Esraa.BeltJava.models.User;
import com.Esraa.BeltJava.repositories.RatingRepository;

@Service
public class RatingService {
	@Autowired
	private RatingRepository ratingRepository;

	// returns all the projects
	public List<Rating> allRatings() {
		return ratingRepository.findAll();
	}

	public List<Rating> allRatingsAsc() {
		return ratingRepository.findAllByOrderByRankingAsc();
	}

	public List<Rating> allRatingsForShow(Show show) {
		return ratingRepository.findByShowOrderByRankingDesc(show);
	}

	// creates a project
	public Rating createRating(Rating rate, User user, Show show) {
		rate.setCreator(user);
		rate.setShow(show);
		return ratingRepository.save(rate);
	}

	public double count(Show show) {
		double sum = 0;
		List<Rating> rates = ratingRepository.findByShow(show);
		for (Rating rating : rates) {
			sum += rating.getRanking();
		}
		return sum / (rates.size());
	}

	// retrieves a project
	// public Rating findRating(Long id) {
	// Optional<Rating> optionalShow = ratingRepository.findById(id);
	// // Optional<Rating> optionalShow1= ratingRepository.findByUserId(id);
	// if (optionalShow.isPresent()) {
	// return optionalShow.get();
	// } else {
	// return null;
	// }
	// }

}
