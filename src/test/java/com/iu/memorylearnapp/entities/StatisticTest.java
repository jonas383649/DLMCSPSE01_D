package com.iu.memorylearnapp.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StatisticTest {

    private Statistic entity;

    @BeforeEach
    public void setUp() {
        entity = new Statistic();
    }

    @Test
    public void testConstructor() {
        final var cardSet = mock(CardSet.class);

        entity = new Statistic();
        assertNull(entity.getCardSet());

        entity = new Statistic(cardSet);
        assertSame(cardSet, entity.getCardSet());
    }

    @Test
    public void testId() {
        final var id = 1L;
        entity.setId(id);
        assertSame(id, entity.getId());
    }

    @Test
    public void testCardSet() {
        final var cardSet = new CardSet();
        entity.setCardSet(cardSet);
        assertSame(cardSet, entity.getCardSet());
    }

    @Test
    public void testLastTime() {
        final var time = 1.0;
        entity.setLastTime(time);
        assertEquals(time, entity.getLastTime());
    }

    @Test
    public void testLastMoves() {
        final var moves = 1;
        entity.setLastMoves(moves);
        assertSame(moves, entity.getLastMoves());
    }

    @Test
    public void testBestTime() {
        final var time = 1.0;
        entity.setBestTime(time);
        assertEquals(time, entity.getBestTime());
    }

    @Test
    public void testBestMoves() {
        final var moves = 1;
        entity.setBestMoves(moves);
        assertSame(moves, entity.getBestMoves());
    }

    @Test
    public void testAvgTime() {
        final var time = 1.0;
        entity.setAvgTime(time);
        assertEquals(time, entity.getAvgTime());
    }

    @Test
    public void testAvgMoves() {
        final var moves = 1;
        entity.setAvgMoves(moves);
        assertSame(moves, entity.getAvgMoves());
    }

    @Test
    public void testRepetitions() {
        final var repetitions = 1;
        entity.setRepetitions(repetitions);
        assertSame(repetitions, entity.getRepetitions());
    }

    @Test
    void testToString() {
        final var expected = "Statistic[" + String.join(", ",
            "id=null",
            "lastTime=0.0",
            "lastMoves=0",
            "bestTime=0.0",
            "bestMoves=0",
            "avgTime=0.0",
            "avgMoves=0",
            "repetitions=0"
        ) + "]";

        assertEquals(expected, entity.toString());
    }
}