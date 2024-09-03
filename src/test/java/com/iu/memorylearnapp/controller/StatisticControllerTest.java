package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.entities.Statistic;
import com.iu.memorylearnapp.services.StageService;
import javafx.scene.text.Text;
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

    private Text time;

    private Text moves;

    private Text bestTime;

    private Text bestMoves;

    private Text avgTime;

    private Text avgMoves;

    private CardSet cardSet;

    @BeforeEach
    public void setUp() {
        time = mock(Text.class);
        moves = mock(Text.class);
        bestTime = mock(Text.class);
        bestMoves = mock(Text.class);
        avgTime = mock(Text.class);
        avgMoves = mock(Text.class);
        cardSet = mock(CardSet.class);

        ReflectionTestUtils.setField(controller, "time", time);
        ReflectionTestUtils.setField(controller, "moves", moves);
        ReflectionTestUtils.setField(controller, "bestTime", bestTime);
        ReflectionTestUtils.setField(controller, "bestMoves", bestMoves);
        ReflectionTestUtils.setField(controller, "avgTime", avgTime);
        ReflectionTestUtils.setField(controller, "avgMoves", avgMoves);
        ReflectionTestUtils.setField(controller, "cardSet", cardSet);
    }

    @Test
    public void testInitialize() {
        when(cardSet.getStatistic()).thenReturn(mock(Statistic.class));

        controller.initialize();

        verify(time).setText(anyString());
        verify(moves).setText(anyString());
        verify(bestTime).setText(anyString());
        verify(bestMoves).setText(anyString());
        verify(avgTime).setText(anyString());
        verify(avgMoves).setText(anyString());
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