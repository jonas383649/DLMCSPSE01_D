package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.Card;
import com.iu.memorylearnapp.entities.CardPair;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.ResourceService;
import com.iu.memorylearnapp.services.StageService;
import com.iu.memorylearnapp.services.StatisticService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.List;
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameControllerTest extends ApplicationTest {

    @InjectMocks
    private GameController controller;

    @Mock
    private ResourceService resourceService;

    @Mock
    private StatisticController statisticController;

    @Mock
    private StatisticService statisticService;

    @Mock
    private StageService stageService;

    private GridPane gridPane;
    
    private Timer timer;

    @BeforeEach
    public void setUp() {
        final var pairsLabel = mock(Label.class);
        final var timeLabel = mock(Label.class);
        final var movesLabel = mock(Label.class);

        gridPane = mock(GridPane.class);
        timer = mock(Timer.class);

        ReflectionTestUtils.setField(controller, "gridPane", gridPane);
        ReflectionTestUtils.setField(controller, "movesLabel", movesLabel);
        ReflectionTestUtils.setField(controller, "pairsLabel", pairsLabel);
        ReflectionTestUtils.setField(controller, "timeLabel", timeLabel);
        ReflectionTestUtils.setField(controller, "timer", timer);
    }

    @Test
    public void testInitialize() throws Exception {
        final var cardSet = mockCardSet();
        final var loader = mockLoader();

        ReflectionTestUtils.setField(controller, "cardSet", cardSet);
        ReflectionTestUtils.setField(controller, "goal", 1);

        controller.initialize();

        verify(gridPane, times(2)).add(any(), anyInt(), anyInt());
        verify(loader, times(2)).load();
    }

    @Test
    public void testQuitGame() {
        controller.quitGame();

        verify(stageService).show(any());
        verify(timer).cancel();
    }

    @Test
    public void testCheckCardWhenNoCardIsSelected() {
        final var cardController = mock(CardController.class);

        controller.checkCard(cardController);

        final var selectedField = ReflectionTestUtils.getField(controller, "selected");
        assertNotNull(selectedField);
    }

    @Test
    public void testCheckCardWhenMatchingCardIsSelected() {
        final var cardController = mockCardController();
        final var selected = mockCardController();

        final var firstCard = cardController.getCard();
        final var secondCard = selected.getCard();
        final var cardPair = mock(CardPair.class);

        when(firstCard.getCardPair()).thenReturn(cardPair);
        when(secondCard.getCardPair()).thenReturn(cardPair);

        ReflectionTestUtils.setField(controller, "selected", selected);

        controller.checkCard(cardController);

        verify(cardController).unmark();
        verify(selected).unmark();

        final var selectedField = ReflectionTestUtils.getField(controller, "selected");
        assertNull(selectedField);
    }

    @Test
    public void testCheckCardWhenNoMatchingCardIsSelected() {
        final var cardController = mockCardController();
        final var selected = mockCardController();

        ReflectionTestUtils.setField(controller, "selected", selected);

        controller.checkCard(cardController);

        verify(cardController).cover();
        verify(selected).cover();

        final var selectedField = ReflectionTestUtils.getField(controller, "selected");
        assertNull(selectedField);
    }

    @Test
    public void testSetCardSet() {
        final var cardSet = mock(CardSet.class);
        controller.setCardSet(cardSet);
        final var cardSetField = ReflectionTestUtils.getField(controller, "cardSet");
        assertSame(cardSet, cardSetField);
    }

    @Test
    public void testSetGoal() {
        final var goal = 10;
        controller.setGoal(goal);
        final var goalField = ReflectionTestUtils.getField(controller, "goal");
        assertSame(goal, goalField);
    }

    @Test
    public void testStopGame() {
        ReflectionTestUtils.invokeMethod(controller, "stopGame");
        verify(statisticController).setCardSet(any());
        verify(statisticService).updateStatistic(any(), anyInt(), anyInt());
        verify(stageService).showPopover(any());
    }

    private CardSet mockCardSet() {
        final var cardSet = mock(CardSet.class);
        final var cardPair = mock(CardPair.class);
        final var firstCard = mock(Card.class);
        final var secondCard = mock(Card.class);

        when(cardSet.getCardPairs()).thenReturn(List.of(cardPair));
        when(cardPair.getFirstCard()).thenReturn(firstCard);
        when(cardPair.getSecondCard()).thenReturn(secondCard);

        return cardSet;
    }

    private FXMLLoader mockLoader() throws Exception {
        final var loader = mock(FXMLLoader.class);
        final var cardController = mock(CardController.class);
        final var root = mock(Node.class);

        when(resourceService.createLoader(any())).thenReturn(loader);
        when(loader.getController()).thenReturn(cardController);
        when(loader.load()).thenReturn(root);

        return loader;
    }

    private CardController mockCardController() {
        final var cardController = mock(CardController.class);
        final var cardPair = mock(CardPair.class);
        final var card = mock(Card.class);

        when(cardController.getCard()).thenReturn(card);
        when(card.getCardPair()).thenReturn(cardPair);

        return cardController;
    }
}