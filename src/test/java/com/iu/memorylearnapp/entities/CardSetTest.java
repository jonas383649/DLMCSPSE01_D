package com.iu.memorylearnapp.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardSetTest {

    private CardSet entity;

    @BeforeEach
    public void setUp() {
        entity = new CardSet();
    }

    @Test
    public void testCardSetId() {
        final var id = 1L;
        entity.setId(id);
        assertEquals(id, entity.getId());
    }

    @Test
    public void testCardSetName() {
        final var name = "name";
        entity.setName(name);
        assertEquals(name, entity.getName());
    }

    @Test
    public void testCardSetToString() {
        final var expected = "CardSet[id=1, name=name]";
        entity.setId(1L);
        entity.setName("name");
        assertEquals(expected, entity.toString());
    }
}
