package com.Esraa.BeltJava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.Esraa.BeltJava.models.Show;
import com.Esraa.BeltJava.models.User;
import com.Esraa.BeltJava.repositories.ShowRepository;

@Service
public class ShowService {
	// adding the Show repository as a dependency
	private final ShowRepository showRepository;

	public ShowService(ShowRepository showRepository) {
		this.showRepository = showRepository;
	}

	// returns all the shows
	public List<Show> allShows() {
		return showRepository.findAll();
	}

	// public List<Show> allShowsNotContain(User user) {
	// return showRepository.findByMembersNotContaining(user);
	// }

	// public List<Show> allShowsContain(User user) {
	// return showRepository.findByMembersContaining(user);
	// }

	public List<Show> allShowsByUser(User user) {
		return showRepository.findByUser(user);
	}

	// public List<Show> MembersInShow(User user) {
	// return showRepository.findByMembers(user);
	// }

	// creates a Show
	public Show createShow(Show b) {
		return showRepository.save(b);
	}

	public List<Show> titlesShow(String title) {
		return showRepository.findByTitle(title);
	}

	// retrieves a Show
	public Show findShow(Long id) {
		Optional<Show> optionalShow = showRepository.findById(id);
		if (optionalShow.isPresent()) {
			return optionalShow.get();
		} else {
			return null;
		}
	}

	public Show updateShow(Show Show, Long id) {// update
		Show optionalShow = findShow(id);
		optionalShow.setTitle(Show.getTitle());
		optionalShow.setDescription(Show.getDescription());
		optionalShow.setNetwork(Show.getNetwork());
		return showRepository.save(optionalShow);
	}



	public void deleteShow(Long id) {
		showRepository.deleteById(id);
	}

}
