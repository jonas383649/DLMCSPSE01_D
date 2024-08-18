package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.Card;
import com.iu.memorylearnapp.entities.CardPair;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditorItemControllerTest extends ApplicationTest {

    @InjectMocks
    private EditorItemController controller;

    @Mock
    private EditorController editorController;

    private TextField firstTextField;

    private TextField secondTextField;

    private CardPair cardPair;

    @BeforeEach
    public void setUp() {
        firstTextField = mock(TextField.class);
        secondTextField = mock(TextField.class);
        cardPair = mock(CardPair.class);

        ReflectionTestUtils.setField(controller, "firstTextField", firstTextField);
        ReflectionTestUtils.setField(controller, "secondTextField", secondTextField);
        ReflectionTestUtils.setField(controller, "cardPair", cardPair);
    }

    @Test
    public void testInitialize() {
        final var firstTextProperty = mock(StringProperty.class);
        final var secondTextProperty = mock(StringProperty.class);

        when(firstTextField.textProperty()).thenReturn(firstTextProperty);
        when(secondTextField.textProperty()).thenReturn(secondTextProperty);

        controller.initialize();

        verify(firstTextProperty).addListener(any(ChangeListener.class));
        verify(secondTextProperty).addListener(any(ChangeListener.class));
    }

    @Test
    public void testDeleteCardPair() {
        controller.deleteCardPair();
        verify(editorController).deleteCardPair(cardPair);
    }

    @Test
    public void testSetCardPairWhenContentsAreValid() {
        final var content = "content";
        final var cardPair = mockCardPair(content);

        controller.setCardPair(cardPair);

        final var cardSetField = ReflectionTestUtils.getField(controller, "cardPair");
        assertSame(cardPair, cardSetField);

        verify(firstTextField).setText(content);
        verify(firstTextField).setStyle(anyString());
        verify(firstTextField).setTooltip(null);

        verify(secondTextField).setText(content);
        verify(secondTextField).setStyle(anyString());
        verify(secondTextField).setTooltip(null);
    }

    @Test
    public void testSetCardPairWhenContentsAreInvalid() {
        final var content = "";
        final var cardPair = mockCardPair(content);

        controller.setCardPair(cardPair);

        final var cardSetField = ReflectionTestUtils.getField(controller, "cardPair");
        assertSame(cardPair, cardSetField);

        verify(firstTextField).setText(content);
        verify(firstTextField).setStyle(anyString());
        verify(firstTextField).setTooltip(any());

        verify(secondTextField).setText(content);
        verify(secondTextField).setStyle(anyString());
        verify(secondTextField).setTooltip(any());
    }

    private CardPair mockCardPair(final String content) {
        final var firstCard = mock(Card.class);
        final var secondCard = mock(Card.class);
        final var cardPair = mock(CardPair.class);

        when(cardPair.getFirstCard()).thenReturn(firstCard);
        when(cardPair.getSecondCard()).thenReturn(secondCard);

        when(firstCard.getContent()).thenReturn(content);
        when(secondCard.getContent()).thenReturn(content);

        when(firstTextField.getText()).thenReturn(content);
        when(secondTextField.getText()).thenReturn(content);

        return cardPair;
    }
}