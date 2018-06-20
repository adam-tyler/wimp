package com.el.ally.wimp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.el.ally.wimp.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
