package com.el.ally.wimp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Actor {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=75, nullable=false)
	private String firstName;
	
	@Column(length=75, nullable=true)
	private String lastName;
	
	@Column(nullable=true)
	private long activeSinceYear;
	
	@Column(nullable=true)
	private Date birthDate;
	
	@JsonIgnoreProperties("actors")
	@ManyToMany(mappedBy="actors")
	private List<Movie> movies;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Award> awards;
	
	public Actor() {
		this.movies = new ArrayList<Movie>();
		this.awards = new ArrayList<Award>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getActiveSinceYear() {
		return activeSinceYear;
	}

	public void setActiveSinceYear(long activeSinceYear) {
		this.activeSinceYear = activeSinceYear;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public void addMovie(Movie movie) {
		this.movies.add(movie);
	}
	
	public List<Movie> getMovies() {
		return this.movies;
	}

	public void addAward(Award award) {
		this.awards.add(award);
	}

	public List<Award> getAwards() {
		return this.awards;
	}
	
}
