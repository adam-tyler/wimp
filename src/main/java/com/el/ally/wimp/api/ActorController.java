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
import com.el.ally.wimp.models.Award;
import com.el.ally.wimp.repositories.ActorRepository;
import com.el.ally.wimp.repositories.AwardRepository;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

	@Autowired
	private ActorRepository actorRepository;;
	
	public ActorController(ActorRepository a) {
		this.actorRepository = a;
	}
	
	@GetMapping("")
	public List<Actor> getAll() {
		return actorRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Actor getOne(@PathVariable long id) {
		return actorRepository.findOne(id);
	}
	
	@PostMapping("")
	public Actor create(@RequestBody Actor newActor) {
		return actorRepository.save(newActor);
	}
	
	@PutMapping("{id}")
	public Actor update(@RequestBody Actor updatedActor, @PathVariable long id) {
		Actor existing = actorRepository.findOne(id);
		if (existing == null) {
			return null;
		}
		
		updatedActor.setId(id);
		return actorRepository.save(updatedActor);
	}
	
	@DeleteMapping("{id}")
	public Actor delete(@PathVariable long id) {
		Actor actor = actorRepository.findOne(id);
		if (actor == null) {
			return null;
		}
		actorRepository.delete(actor);
		return actor;
	}
	
	@PostMapping("{id}/awards")
	public Actor createAward(@RequestBody Award award, @PathVariable long id) {
		Actor actor = actorRepository.findOne(id);
		
		if (actor == null) {
			return null;
		}
		
		actor.addAward(award);
		return actorRepository.save(actor);
	}
}
