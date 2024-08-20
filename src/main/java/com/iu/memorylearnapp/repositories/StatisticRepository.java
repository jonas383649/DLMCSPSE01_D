package com.iu.memorylearnapp.repositories;

import com.iu.memorylearnapp.entities.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
}