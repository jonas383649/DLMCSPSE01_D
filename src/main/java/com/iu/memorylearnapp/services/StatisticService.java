package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.entities.Statistic;
import com.iu.memorylearnapp.repositories.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for updating and managing statistics related to card sets.
 *
 * <p>The {@link StatisticService} handles updates to the {@link Statistic} associated with a given
 * {@link CardSet}. It calculates and updates various statistical metrics such as best times,
 * average times, best moves, average moves, and the number of repetitions.</p>
 */
@Service
public class StatisticService {

    @Autowired
    private StatisticRepository statisticRepository;

    /**
     * Updates the statistics for the given card set based on the provided time and moves.
     *
     * <p>This method updates the last time and moves, checks and sets new best values if applicable,
     * recalculates average times and moves, and increments the repetition count. The updated
     * statistics are then saved to the repository.</p>
     */
    public void updateStatistic(final CardSet cardSet, final int time, final int moves) {
        final var statistic = cardSet.getStatistic();

        updateTimes(statistic, time);
        updateMoves(statistic, moves);
        updateRepetitions(statistic);

        statisticRepository.save(statistic);
    }

    private void updateTimes(final Statistic statistic, final int time) {
        statistic.setLastTime(time);

        if (time < statistic.getBestTime() || statistic.getBestTime() == 0) {
            statistic.setBestTime(time);
        }

        final var totalTime = statistic.getAvgTime() * statistic.getRepetitions() + time;
        final var newAvgTime = totalTime / (statistic.getRepetitions() + 1);
        statistic.setAvgTime(newAvgTime);
    }

    private void updateMoves(final Statistic statistic, final int moves) {
        statistic.setLastMoves(moves);

        if (moves < statistic.getBestMoves() || statistic.getBestMoves() == 0) {
            statistic.setBestMoves(moves);
        }

        final var totalMoves = statistic.getAvgMoves() * statistic.getRepetitions() + moves;
        final var newAvgMoves = totalMoves / (statistic.getRepetitions() + 1);
        statistic.setAvgMoves(newAvgMoves);
    }

    private void updateRepetitions(final Statistic statistic) {
        statistic.setRepetitions(statistic.getRepetitions() + 1);
    }
}
