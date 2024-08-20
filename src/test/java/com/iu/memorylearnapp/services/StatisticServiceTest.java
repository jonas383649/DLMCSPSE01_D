package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.entities.Statistic;
import com.iu.memorylearnapp.repositories.StatisticRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatisticServiceTest extends ApplicationTest {

    @InjectMocks
    private StatisticService service;

    @Mock
    private StatisticRepository statisticRepository;

    @Test
    public void testUpdateStatistic() {
        final var cardSet = mock(CardSet.class);
        final var statistic = mock(Statistic.class);

        when(cardSet.getStatistic()).thenReturn(statistic);

        service.updateStatistic(cardSet, 1, 1);

        verify(statistic).setLastTime(1);
        verify(statistic).setLastMoves(1);
        verify(statistic).setBestTime(1);
        verify(statistic).setBestMoves(1);
        verify(statistic).setAvgTime(1);
        verify(statistic).setAvgMoves(1);
        verify(statistic).setRepetitions(1);
        verify(statisticRepository).save(any());
    }
}