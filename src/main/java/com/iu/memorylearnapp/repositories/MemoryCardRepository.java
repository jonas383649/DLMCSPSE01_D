package com.iu.memorylearnapp.repositories;

import com.iu.memorylearnapp.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryCardRepository extends JpaRepository<Card, Long> {
}
