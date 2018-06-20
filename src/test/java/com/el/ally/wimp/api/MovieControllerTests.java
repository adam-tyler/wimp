package com.el.ally.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.el.ally.wimp.models.Actor;
import com.el.ally.wimp.models.Movie;
import com.el.ally.wimp.repositories.ActorRepository;
import com.el.ally.wimp.repositories.MovieRepository;

public class MovieControllerTests {
	
	private MovieController controller;
	
	@Mock
	private MovieRepository movieRepo;
	
	@Mock
	private ActorRepository actorRepo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new MovieController(movieRepo, actorRepo);
	}
	
	@Test
    public void getAllMovies_givenNoMovies_shouldReturnEmptyList() {
        // Arrange
        ArrayList<Movie> actors = new ArrayList<Movie>();
        when(movieRepo.findAll()).thenReturn(actors);

        // Act
        List<Movie> actual = controller.getAll();

        // Assert
        assertThat(actual).hasSize(actors.size());
        verify(movieRepo).findAll();
    }
    
    @Test
    public void getAllMovies_givenMovies_shouldReturnAllMovies() {
    	ArrayList<Movie> movies = new ArrayList<Movie>();
    	movies.add(new Movie());
    	movies.add(new Movie());
    	when(movieRepo.findAll()).thenReturn(movies);
    	
    	List<Movie> actual = controller.getAll();
    	
    	assertThat(actual).hasSize(movies.size());
    	verify(movieRepo).findAll();
    }
    
    @Test
    public void getOneMovie_givenMovieDoesNotExist_shouldReturnNull() {
    	when(movieRepo.findOne(1L)).thenReturn(null);
    	
    	Movie actual = controller.getOne(1L);
    	
    	assertThat(actual).isEqualTo(null);
    	verify(movieRepo).findOne(1L);
    }

    @Test
    public void getOneMovie_givenMovie_shouldReturnMovie() {
        // Arrange
        Movie movie = new Movie();
        when(movieRepo.findOne(1L)).thenReturn(movie);

        // Act
        Movie actual = controller.getOne(1L);

        // Assert
        assertThat(actual).isSameAs(movie);
        verify(movieRepo).findOne(1L);
    }

    @Test
    public void create_givenMovie_shouldReturnMovie() {
        // Arrange
        Movie movie = new Movie();
        when(movieRepo.save(movie)).thenReturn(movie);

        // Act
        Movie actual = controller.create(movie);

        // Assert
        assertThat(actual).isSameAs(movie);
        verify(movieRepo).save(movie);
    }
    
    @Test
    public void update_givenMovieDoesNotExist_shouldReturnNull() {
    	Movie movie = new Movie();
    	when(movieRepo.findOne(2L)).thenReturn(null);
    	
    	Movie actual = controller.update(movie, 2L);
    	
    	assertThat(actual).isEqualTo(null);
    	verify(movieRepo).findOne(2L);
    }
    
    @Test
    public void update_givenExistingMovie_shouldUpdateAndReturnMovie() {
    	Movie movie = new Movie();
    	when(movieRepo.findOne(1L)).thenReturn(movie);
    	when(movieRepo.save(movie)).thenReturn(movie);
    	
    	Movie actual = controller.update(movie, 1L);
    	
    	assertThat(actual).isSameAs(movie);
    	verify(movieRepo).findOne(1L);
    	verify(movieRepo).save(movie);
    }
    
    @Test
    public void delete_givenMovieDoesNotExist_shouldReturnNull() {
    	when(movieRepo.findOne(1L)).thenReturn(null);
    	
    	Movie actual = controller.delete(1L);
    	
    	assertThat(actual).isEqualTo(null);
    	verify(movieRepo).findOne(1L);
    }
    
    @Test
    public void delete_givenMovie_shouldDeleteAndReturnMovie() {
    	Movie movie = new Movie();
    	when(movieRepo.findOne(1L)).thenReturn(movie);
    	
    	Movie actual = controller.delete(1L);
    	
    	assertThat(actual).isSameAs(movie);
    	verify(movieRepo).findOne(1L);
    	verify(movieRepo).delete(movie);
    }
    
    @Test
    public void addActor_givenActorDoesNotExist_shouldReturnNull() {
    	Movie movie = new Movie();
    	Actor actor = new Actor();
    	movie.setId(2L);
    	actor.setId(1L);
    	when(actorRepo.findOne(1L)).thenReturn(null);
    	when(movieRepo.findOne(2L)).thenReturn(movie);
    	
    	Movie actual = controller.addActor(actor, movie.getId());
    	
    	assertThat(actual).isEqualTo(null);
    	verify(movieRepo).findOne(2L);
    	verify(actorRepo).findOne(1L);
    }
    
    @Test
    public void addActor_givenMovieDoesNotExist_shouldReturnNull() {
    	Movie movie = new Movie();
    	Actor actor = new Actor();
    	movie.setId(2L);
    	actor.setId(1L);
    	when(actorRepo.findOne(1L)).thenReturn(actor);
    	when(movieRepo.findOne(2L)).thenReturn(null);
    	
    	Movie actual = controller.addActor(actor, movie.getId());
    	
    	assertThat(actual).isEqualTo(null);
    	verify(movieRepo).findOne(2L);
    	verify(actorRepo).findOne(1L);
    }
    
    @Test
    public void addActor_givenActorAndMovie_shouldReturnMovieWithNewActor() {
    	Movie movie = new Movie();
    	Actor actor = new Actor();
    	movie.setId(2L);
    	actor.setId(1L);
    	when(actorRepo.findOne(1L)).thenReturn(actor);
    	when(movieRepo.findOne(2L)).thenReturn(movie);
    	
    	Movie actual = controller.addActor(actor, movie.getId());
    	
    	assertThat(actual).isSameAs(movie);
    	assertThat(actual.getActors().size()).isEqualTo(1);
    	verify(movieRepo).findOne(2L);
    	verify(actorRepo).findOne(1L);
    }
}
