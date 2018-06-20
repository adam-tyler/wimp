package com.el.ally.wimp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Movie {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=300, nullable=false)
	private String title;
	
	@Column(nullable=true)
	private Date releaseDate;
	
	@Column(nullable=true)
	private long budget;
	
	@Column(length=500, nullable=false)
	private String distributor;
	
	@JsonIgnoreProperties("movies")
	@ManyToMany
	private List<Actor> actors;

	public Movie() {
		this.actors = new ArrayList<Actor>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	
	public void addActor(Actor actor) {
		this.actors.add(actor);
	}
	
	public List<Actor> getActors() {
		return this.actors;
	}
}
