package com.iu.memorylearnapp.repositories;

import com.iu.memorylearnapp.entities.CardPair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryPairRepository extends JpaRepository<CardPair, Long> {
}
