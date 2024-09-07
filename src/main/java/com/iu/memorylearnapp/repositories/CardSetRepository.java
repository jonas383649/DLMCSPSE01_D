package com.iu.memorylearnapp.repositories;

import com.iu.memorylearnapp.entities.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link CardSet} entities.
 */
public interface CardSetRepository extends JpaRepository<CardSet, Long> {
}
