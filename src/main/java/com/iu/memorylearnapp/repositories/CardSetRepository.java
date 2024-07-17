package com.iu.memorylearnapp.repositories;

import com.iu.memorylearnapp.entities.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardSetRepository extends JpaRepository<CardSet, Long> {
}
