package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.Card;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CardControllerTest extends ApplicationTest {

    @InjectMocks
    private CardController controller;

    @Mock
    private GameController gameController;

    private Button button;

    private Card card;

    private PauseTransition coverDelay;

    @BeforeEach
    public void setUp() {
        button = mock(Button.class);
        card = mock(Card.class);
        coverDelay = mock(PauseTransition.class);

        ReflectionTestUtils.setField(controller, "button", button);
        ReflectionTestUtils.setField(controller, "card", card);
        ReflectionTestUtils.setField(controller, "coverDelay", coverDelay);
    }

    @Test
    public void testInitialize() {
        controller.initialize();

        final var coverDelayField = ReflectionTestUtils.getField(controller, "coverDelay");

        assertNotSame(coverDelayField, this.coverDelay);
        assertNotNull(coverDelayField);
    }

    @Test
    public void testReveal() {
        Card card = new Card();
        card.setContent("Test");
        controller.setCard(card);

        controller.reveal();

        verify(coverDelay).stop();
        verify(button).setDisable(true);
        verify(button).setText(anyString());
        verify(button).setBorder(any());
        verify(gameController).checkCard(any());
    }

    @Test
    public void testCover() {
        controller.cover();
        verify(coverDelay).play();
    }

    @Test
    public void testUnmark() {
        controller.unmark();
        verify(button).setBorder(null);
    }


    @Test
    public void testCard() {
        assertSame(card, controller.getCard());
        controller.setCard(null);
        assertNull(controller.getCard());
    }

    @Test
    public void testPerformCover() {
        ReflectionTestUtils.invokeMethod(controller, "performCover");
        verify(button).setText(null);
        verify(button).setBorder(null);
        verify(button).setDisable(false);
    }
}