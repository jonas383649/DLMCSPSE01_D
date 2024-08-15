package com.iu.memorylearnapp.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CardPairTest {

    private CardPair entity;

    @BeforeEach
    public void setUp() {
        entity = new CardPair();
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
    public void testFirstCard() {
        final var firstCard = new Card();
        entity.setFirstCard(firstCard);
        assertSame(firstCard, entity.getFirstCard());
    }

    @Test
    public void testSecondCard() {
        final var secondCard = new Card();
        entity.setSecondCard(secondCard);
        assertSame(secondCard, entity.getSecondCard());
    }

    @Test
    public void testToString() {
        final var expected = "CardPair[id=1, firstCard=null, secondCard=null]";
        entity.setId(1L);
        assertEquals(expected, entity.toString());
    }
}