package com.iu.memorylearnapp.controller;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class MenuItemControllerTest extends ApplicationTest {

    private MenuItemController controller;

    private HBox content;

    private Text name;

    private Button editButton;

    @BeforeEach
    void setUp() {
        controller = new MenuItemController();

        content = mock(HBox.class);
        name = mock(Text.class);
        editButton = mock(Button.class);

        ReflectionTestUtils.setField(controller, "content", content);
        ReflectionTestUtils.setField(controller, "name", name);
        ReflectionTestUtils.setField(controller, "editButton", editButton);
    }
}
