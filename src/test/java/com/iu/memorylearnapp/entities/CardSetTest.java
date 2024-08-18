package com.iu.memorylearnapp.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardSetTest {

    private CardSet entity;

    @BeforeEach
    public void setUp() {
        entity = new CardSet();
    }

    @Test
    public void testConstructor() {
        final var cardPairs = List.of(new CardPair());
        final var name = "name";

        entity = new CardSet();
        assertEquals(0, entity.getCardPairs().size());
        assertNull(entity.getName());

        entity = new CardSet(name);
        assertEquals(0, entity.getCardPairs().size());
        assertSame(name, entity.getName());

        entity = new CardSet(name, cardPairs);
        assertSame(cardPairs, entity.getCardPairs());
        assertSame(name, entity.getName());
    }

    @Test
    public void testId() {
        final var id = 1L;
        entity.setId(id);
        assertSame(id, entity.getId());
    }

    @Test
    public void testCardPairs() {
        final var cardPairs = List.of(new CardPair());
        entity.setCardPairs(cardPairs);
        assertEquals(cardPairs, entity.getCardPairs());
    }

    @Test
    public void testName() {
        final var name = "name";
        entity.setName(name);
        assertSame(name, entity.getName());
    }

    @Test
    public void testToString() {
        final var expected = "CardSet[id=1, name=name, cardPairs=[]]";
        entity.setId(1L);
        entity.setName("name");
        assertEquals(expected, entity.toString());
    }
}
