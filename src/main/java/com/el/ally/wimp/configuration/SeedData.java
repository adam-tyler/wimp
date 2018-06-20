package com.el.ally.wimp.configuration;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.el.ally.wimp.models.Actor;
import com.el.ally.wimp.models.Movie;
import com.el.ally.wimp.repositories.ActorRepository;
import com.el.ally.wimp.repositories.MovieRepository;

@Configuration
@Profile("development")
public class SeedData {

	public SeedData(ActorRepository actorRepository, MovieRepository movieRepository) {
		Actor actor = new Actor();
		actor.setActiveSinceYear(1987);
		actor.setBirthDate(new Date());
		actor.setFirstName("Mr.");
		actor.setLastName("Actor");
		actorRepository.save(actor);
		
		Movie movie = new Movie();
		movie.setBudget(1000);
		movie.setDistributor("Buena Vista");
		movie.setReleaseDate(new Date());
		movie.setTitle("Mulan");
		movieRepository.save(movie);
	}
	
}
