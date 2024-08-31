package com.iu.memorylearnapp.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CardTest {

    private Card entity;

    @BeforeEach
    public void setUp() {
        entity = new Card();
    }

    @Test
    public void testConstructor() {
        final var cardPair = mock(CardPair.class);

        entity = new Card();
        assertNull(entity.getCardPair());

        entity = new Card(cardPair);
        assertSame(cardPair, entity.getCardPair());
    }

    @Test
    public void testId() {
        final var id = 1L;
        entity.setId(id);
        assertSame(id, entity.getId());
    }

    @Test
    public void testCardPair() {
        final var cardPair = new CardPair();
        entity.setCardPair(cardPair);
        assertSame(cardPair, entity.getCardPair());
    }

    @Test
    public void testContent() {
        final var content = "content";
        entity.setContent(content);
        assertSame(content, entity.getContent());
    }

    @Test
    public void testImagePath() {
        final var imagePath = "imagePath";
        entity.setImagePath(imagePath);
        assertSame(imagePath, entity.getImagePath());
    }

    @Test
    public void testToString() {
        final var expected = "Card[id=1, content=content, imagePath=imagePath]";
        entity.setId(1L);
        entity.setContent("content");
        entity.setImagePath("imagePath");
        assertEquals(expected, entity.toString());
    }
}