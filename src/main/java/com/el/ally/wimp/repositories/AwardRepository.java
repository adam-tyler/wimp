package com.el.ally.wimp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.el.ally.wimp.models.Award;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {
}
