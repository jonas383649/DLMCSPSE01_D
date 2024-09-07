package com.iu.memorylearnapp.repositories;

import com.iu.memorylearnapp.entities.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Statistic} entities.
 */
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
}