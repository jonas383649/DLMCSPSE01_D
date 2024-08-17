package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.StageService;
import javafx.scene.control.Button;
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
class DifficultyControllerTest extends ApplicationTest {

    @InjectMocks
    private DifficultyController controller;

    @Mock
    private GameController gameController;

    @Mock
    private StageService stageService;

    private Button easyButton;

    private Button mediumButton;

    private Button hardButton;

    private CardSet cardSet;

    @BeforeEach
    public void setUp() {
        easyButton = mock(Button.class);
        mediumButton = mock(Button.class);
        hardButton = mock(Button.class);
        cardSet = mock(CardSet.class);

        ReflectionTestUtils.setField(controller, "easyButton", easyButton);
        ReflectionTestUtils.setField(controller, "mediumButton", mediumButton);
        ReflectionTestUtils.setField(controller, "hardButton", hardButton);
        ReflectionTestUtils.setField(controller, "cardSet", cardSet);
    }

    @Test
    public void testInitialize() {
        when(cardSet.getCardPairs()).thenReturn(new ArrayList<>());

        controller.initialize();

        verify(easyButton).setDisable(anyBoolean());
        verify(mediumButton).setDisable(anyBoolean());
        verify(hardButton).setDisable(anyBoolean());
    }

    @Test
    public void testPlayEasy() {
        controller.playEasy();
        checkPlay(2);
    }

    @Test
    public void testPlayMedium() {
        controller.playMedium();
        checkPlay(4);
    }

    @Test
    public void testPlayHard() {
        controller.playHard();
        checkPlay(8);
    }

    @Test
    public void testOnCancel() {
        controller.onCancel();
        verify(stageService).closePopover();
    }

    @Test
    public void testCardSet() {
        final var cardSet = new CardSet();
        controller.setCardSet(cardSet);
        assertSame(cardSet, controller.getCardSet());
    }

    private void checkPlay(final int goal) {
        verify(gameController).setCardSet(cardSet);
        verify(gameController).setGoal(goal);
        verify(stageService).show(any());
    }
}