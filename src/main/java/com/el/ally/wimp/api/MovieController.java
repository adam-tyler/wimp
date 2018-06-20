package com.el.ally.wimp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.el.ally.wimp.models.Actor;
import com.el.ally.wimp.models.Movie;
import com.el.ally.wimp.repositories.ActorRepository;
import com.el.ally.wimp.repositories.MovieRepository;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ActorRepository actorRepository;
	
	public MovieController(MovieRepository m, ActorRepository a) {
		this.movieRepository = m;
		this.actorRepository = a;
	}
	
	@GetMapping("")
	public List<Movie> getAll() {
		return movieRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Movie getOne(@PathVariable long id) {
		return movieRepository.findOne(id);
	}
	
	@PostMapping("")
	public Movie create(@RequestBody Movie newMovie) {
		return movieRepository.save(newMovie);
	}
	
	@PutMapping("{id}")
	public Movie update(@RequestBody Movie updatedMovie, @PathVariable long id) {
		Movie existingMovie = movieRepository.findOne(id);
		
		if (existingMovie == null) {
			return null;
		}
		updatedMovie.setId(id);
		return movieRepository.save(updatedMovie);
	}
	
	@DeleteMapping("{id}")
	public Movie delete(@PathVariable long id) {
		Movie movie = movieRepository.findOne(id);
		if (movie == null) {
			return null;
		}
		
		movieRepository.delete(movie);
		return movie;
	}
	
	@PostMapping("{movieId}/actors")
	public Movie addActor(@RequestBody Actor actor, @PathVariable long movieId) {
		Movie movie = movieRepository.findOne(movieId);
		Actor actorObj = actorRepository.findOne(actor.getId());
		
		if (movie == null || actorObj == null) {
			return null;
		}
		
		movie.addActor(actorObj);
		movieRepository.save(movie);
		return movie;
	}
}
