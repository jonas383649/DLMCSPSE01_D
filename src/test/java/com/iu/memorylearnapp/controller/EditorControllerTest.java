package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.CardPair;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.repositories.CardSetRepository;
import com.iu.memorylearnapp.services.ResourceService;
import com.iu.memorylearnapp.services.StageService;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditorControllerTest extends ApplicationTest {

    @InjectMocks
    private EditorController controller;

    @Mock
    private CardSetRepository cardSetRepository;

    @Mock
    private ResourceService resourceService;

    @Mock
    private StageService stageService;

    private ListView<CardPair> cardPairsListView;

    private CardSet cardSet;

    @BeforeEach
    public void setUp() {
        cardPairsListView = mock(ListView.class);
        cardSet = mock(CardSet.class);

        ReflectionTestUtils.setField(controller, "cardPairsListView", cardPairsListView);
        ReflectionTestUtils.setField(controller, "cardSet", cardSet);
    }

    @Test
    public void testInitialize() {
        when(cardSet.getCardPairs()).thenReturn(new ArrayList<>());
        controller.initialize();
        checkListViewUpdates();
    }

    @Test
    public void testAddCardPair() {
        when(cardSet.getCardPairs()).thenReturn(new ArrayList<>());
        controller.addCardPair();
        checkListViewUpdates();
    }

    @Test
    public void testDeleteCardPair() {
        when(cardSet.getCardPairs()).thenReturn(new ArrayList<>());
        controller.deleteCardPair(mock(CardPair.class));
        checkListViewUpdates();
    }

    @Test
    public void testSaveChanges() {
        controller.saveChanges();
        verify(cardSetRepository).save(any());
        verify(stageService).show(any());
    }

    @Test
    public void testCancel() {
        controller.cancel();
        verify(stageService).show(any());
    }

    @Test
    public void testSetCardSet() {
        final var cardSet = mock(CardSet.class);
        controller.setCardSet(cardSet);
        final var cardSetField = ReflectionTestUtils.getField(controller, "cardSet");
        assertSame(cardSet, cardSetField);
    }

    private void checkListViewUpdates() {
        verify(cardPairsListView).setCellFactory(any());
        verify(cardPairsListView).setItems(any());
    }
}