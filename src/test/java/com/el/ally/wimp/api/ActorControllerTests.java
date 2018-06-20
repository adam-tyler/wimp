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

import com.el.ally.wimp.api.ActorController;
import com.el.ally.wimp.models.Actor;
import com.el.ally.wimp.models.Award;
import com.el.ally.wimp.repositories.ActorRepository;

public class ActorControllerTests {

    private ActorController controller;

    @Mock
    private ActorRepository actorRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new ActorController(actorRepo);
    }

    @Test
    public void getAll_actors_givenNoActors_shouldReturnEmptyList() {
        // Arrange
        ArrayList<Actor> actors = new ArrayList<Actor>();
        when(actorRepo.findAll()).thenReturn(actors);

        // Act
        List<Actor> actual = controller.getAll();

        // Assert
        assertThat(actual).hasSize(actors.size());
        verify(actorRepo).findAll();
    }
    
    @Test
    public void getAllActors_givenActors_shouldReturnAllActors() {
    	ArrayList<Actor> actors = new ArrayList<Actor>();
    	actors.add(new Actor());
    	actors.add(new Actor());
    	when(actorRepo.findAll()).thenReturn(actors);
    	
    	List<Actor> actual = controller.getAll();
    	
    	assertThat(actual).hasSize(actors.size());
    	verify(actorRepo).findAll();
    }
    
    @Test
    public void getOneActor_givenActorDoesNotExist_shouldReturnNull() {
    	when(actorRepo.findOne(1L)).thenReturn(null);
    	
    	Actor actual = controller.getOne(1L);
    	
    	assertThat(actual).isEqualTo(null);
    	verify(actorRepo).findOne(1L);
    }

    @Test
    public void getOneActor_givenActor_shouldReturnActor() {
        // Arrange
        Actor actor = new Actor();
        when(actorRepo.findOne(1L)).thenReturn(actor);

        // Act
        Actor actual = controller.getOne(1L);

        // Assert
        assertThat(actual).isSameAs(actor);
        verify(actorRepo).findOne(1L);
    }

    @Test
    public void create_givenActor_shouldReturnActor() {
        // Arrange
        Actor actor = new Actor();
        when(actorRepo.save(actor)).thenReturn(actor);

        // Act
        Actor actual = controller.create(actor);

        // Assert
        assertThat(actual).isSameAs(actor);
        verify(actorRepo).save(actor);
    }
    
    @Test
    public void update_givenActorDoesNotExist_shouldReturnNull() {
    	Actor actor = new Actor();
    	when(actorRepo.findOne(2L)).thenReturn(null);
    	
    	Actor actual = controller.update(actor, 2L);
    	
    	assertThat(actual).isEqualTo(null);
    	verify(actorRepo).findOne(2L);
    }
    
    @Test
    public void update_givenExistingActor_shouldUpdateAndReturnActor() {
    	Actor actor = new Actor();
    	when(actorRepo.findOne(1L)).thenReturn(actor);
    	when(actorRepo.save(actor)).thenReturn(actor);
    	
    	Actor actual = controller.update(actor, 1L);
    	
    	assertThat(actual).isSameAs(actor);
    	verify(actorRepo).findOne(1L);
    	verify(actorRepo).save(actor);
    }
    
    @Test
    public void delete_givenActorDoesNotExist_shouldReturnNull() {
    	when(actorRepo.findOne(1L)).thenReturn(null);
    	
    	Actor actual = controller.delete(1L);
    	
    	assertThat(actual).isEqualTo(null);
    	verify(actorRepo).findOne(1L);
    }
    
    @Test
    public void delete_givenActor_shouldDeleteAndReturnActor() {
    	Actor actor = new Actor();
    	when(actorRepo.findOne(1L)).thenReturn(actor);
    	
    	Actor actual = controller.delete(1L);
    	
    	assertThat(actual).isSameAs(actor);
    	verify(actorRepo).findOne(1L);
    	verify(actorRepo).delete(actor);
    }
    
    @Test
    public void createAward_givenActorDoesNotExist_shouldReturnNull() {
    	Award award = new Award();
    	when(actorRepo.findOne(1L)).thenReturn(null);
    	
    	Actor actual = controller.createAward(award, 1L);
    	
    	assertThat(actual).isEqualTo(null);
    	verify(actorRepo).findOne(1L);
    }
    
    @Test
    public void createAward_givenActor_shouldCreateAwardForActor() {
    	Actor actor = new Actor();
    	Award award = new Award();
    	when(actorRepo.findOne(1L)).thenReturn(actor);
    	when(actorRepo.save(actor)).thenReturn(actor);
    	
    	Actor actual = controller.createAward(award, 1L);
    	
    	assertThat(actual).isSameAs(actor);
    	assertThat(actual.getAwards().size()).isEqualTo(1);
    	verify(actorRepo).findOne(1L);
    	verify(actorRepo).save(actor);
    }

}