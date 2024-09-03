package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.Card;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardControllerTest extends ApplicationTest {

    @InjectMocks
    private CardController controller;

    @Mock
    private GameController gameController;

    private Button button;

    private ImageView imageView;

    private Label content;

    private Card card;

    private PauseTransition coverDelay;

    @BeforeEach
    public void setUp() {
        button = mock(Button.class);
        imageView = mock(ImageView.class);
        content = mock(Label.class);
        card = mock(Card.class);
        coverDelay = mock(PauseTransition.class);

        ReflectionTestUtils.setField(controller, "button", button);
        ReflectionTestUtils.setField(controller, "imageView", imageView);
        ReflectionTestUtils.setField(controller, "content", content);
        ReflectionTestUtils.setField(controller, "card", card);
        ReflectionTestUtils.setField(controller, "coverDelay", coverDelay);
    }

    @Test
    public void testInitialize() {
        when(button.heightProperty()).thenReturn(new SimpleDoubleProperty());
        when(button.widthProperty()).thenReturn(new SimpleDoubleProperty());
        
        when(imageView.fitHeightProperty()).thenReturn(new SimpleDoubleProperty());
        when(content.maxWidthProperty()).thenReturn(new SimpleDoubleProperty());

        controller.initialize();

        final var coverDelayField = ReflectionTestUtils.getField(controller, "coverDelay");

        assertNotSame(coverDelayField, this.coverDelay);
        assertNotNull(coverDelayField);

        verify(button).setMinSize(anyDouble(), anyDouble());

        verify(imageView).fitHeightProperty();
        verify(imageView).setPreserveRatio(anyBoolean());
        verify(imageView).setVisible(anyBoolean());
        verify(imageView).setManaged(anyBoolean());

        verify(content).maxWidthProperty();
        verify(content).setVisible(anyBoolean());
    }

    @Test
    public void testReveal() {
        when(card.getImagePath()).thenReturn("path");
        when(button.getStyleClass()).thenReturn(mock(ObservableList.class));

        controller.reveal();

        verify(coverDelay).stop();
        verify(button, times(1)).setDisable(true);
        verify(button, times(2)).getStyleClass();
        verify(imageView).setVisible(true);
        verify(imageView).setManaged(true);
        verify(content).setVisible(true);
        verify(gameController).checkCard(any());
    }

    @Test
    public void testCover() {
        controller.cover();
        verify(coverDelay).play();
    }

    @Test
    public void testUnmark() {
        when(button.getStyleClass()).thenReturn(mock(ObservableList.class));
        controller.unmark();
        verify(button).getStyleClass();
    }

    @Test
    public void testCard() {
        final var card = mock(Card.class);
        controller.setCard(card);
        assertSame(card, controller.getCard());
    }

    @Test
    public void testPerformCover() {
        when(card.getImagePath()).thenReturn("path");
        when(button.getStyleClass()).thenReturn(mock(ObservableList.class));

        ReflectionTestUtils.invokeMethod(controller, "performCover");

        verify(button, times(1)).setDisable(false);
        verify(button, times(2)).getStyleClass();
        verify(imageView).setVisible(false);
        verify(imageView).setManaged(false);
        verify(content).setVisible(false);
    }

    @Test
    public void testUpdateContent() {
        when(card.getContent()).thenReturn("content");
        ReflectionTestUtils.invokeMethod(controller, "updateContent");
        verify(content).setText(anyString());
    }

    @Test
    public void testUpdateImage() {
        when(card.getImagePath()).thenReturn("path");
        ReflectionTestUtils.invokeMethod(controller, "updateImage");
        verify(imageView).setImage(any());
    }
}