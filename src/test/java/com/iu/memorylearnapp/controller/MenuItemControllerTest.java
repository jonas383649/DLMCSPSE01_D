package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.StageService;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuItemControllerTest extends ApplicationTest {

    @InjectMocks
    private MenuItemController controller;

    @Mock
    private GameController gameController;

    @Mock
    private StageService stageService;

    private HBox content;

    private Text name;

    private Button editButton;

    private CardSet cardSet;

    @BeforeEach
    public void setUp() {
        content = mock(HBox.class);
        name = mock(Text.class);
        editButton = mock(Button.class);
        cardSet = mock(CardSet.class);

        ReflectionTestUtils.setField(controller, "content", content);
        ReflectionTestUtils.setField(controller, "name", name);
        ReflectionTestUtils.setField(controller, "editButton", editButton);
        ReflectionTestUtils.setField(controller, "cardSet", cardSet);
    }

    @Test
    public void testSetCardSet() {
        final var text = "text";
        when(cardSet.getName()).thenReturn(text);
        controller.setCardSet(cardSet);
        verify(name).setText(text);
    }

    @Test
    public void testPlayCardSet() {
        controller.playCardSet();
        verify(gameController).setCardSet(any());
        verify(stageService).show(any());
    }
}
