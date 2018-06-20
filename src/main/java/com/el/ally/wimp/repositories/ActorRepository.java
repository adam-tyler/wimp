package com.el.ally.wimp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.el.ally.wimp.models.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
