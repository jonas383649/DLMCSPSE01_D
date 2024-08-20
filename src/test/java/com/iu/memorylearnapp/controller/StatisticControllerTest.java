package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.entities.Statistic;
import com.iu.memorylearnapp.services.StageService;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatisticControllerTest extends ApplicationTest {

    @InjectMocks
    private StatisticController controller;

    @Mock
    private StageService stageService;

    private Label timeLabel;

    private Label movesLabel;

    private Label bestTimeLabel;

    private Label bestMovesLabel;

    private Label avgTimeLabel;

    private Label avgMovesLabel;

    private CardSet cardSet;

    @BeforeEach
    public void setUp() {
        timeLabel = mock(Label.class);
        movesLabel = mock(Label.class);
        bestTimeLabel = mock(Label.class);
        bestMovesLabel = mock(Label.class);
        avgTimeLabel = mock(Label.class);
        avgMovesLabel = mock(Label.class);
        cardSet = mock(CardSet.class);

        ReflectionTestUtils.setField(controller, "timeLabel", timeLabel);
        ReflectionTestUtils.setField(controller, "movesLabel", movesLabel);
        ReflectionTestUtils.setField(controller, "bestTimeLabel", bestTimeLabel);
        ReflectionTestUtils.setField(controller, "bestMovesLabel", bestMovesLabel);
        ReflectionTestUtils.setField(controller, "avgTimeLabel", avgTimeLabel);
        ReflectionTestUtils.setField(controller, "avgMovesLabel", avgMovesLabel);
        ReflectionTestUtils.setField(controller, "cardSet", cardSet);
    }

    @Test
    public void testInitialize() {
        when(cardSet.getStatistic()).thenReturn(mock(Statistic.class));

        controller.initialize();

        verify(timeLabel).setText(anyString());
        verify(movesLabel).setText(anyString());
        verify(bestTimeLabel).setText(anyString());
        verify(bestMovesLabel).setText(anyString());
        verify(avgTimeLabel).setText(anyString());
        verify(avgMovesLabel).setText(anyString());
    }

    @Test
    public void testReplay() {
        controller.replay();
        verify(stageService).show(any());
    }

    @Test
    public void testShowMenu() {
        controller.showMenu();
        verify(stageService).show(any());
    }

    @Test
    public void testSetCardSet() {
        final var cardSet = mock(CardSet.class);
        controller.setCardSet(cardSet);
        final var cardSetField = ReflectionTestUtils.getField(controller, "cardSet");
        assertSame(cardSet, cardSetField);
    }
}