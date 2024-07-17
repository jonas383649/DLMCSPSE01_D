package com.iu.memorylearnapp.renderer;

import com.iu.memorylearnapp.controller.MenuItemController;
import com.iu.memorylearnapp.entities.CardSet;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuItemCellTest extends ApplicationTest {

    private MenuItemCell cell;

    @Mock
    private HBox root;

    @Mock
    private MenuItemController controller;

    @BeforeEach
    public void setUp() {
        cell = new MenuItemCell();

        ReflectionTestUtils.setField(cell, "root", root);
        ReflectionTestUtils.setField(cell, "controller", controller);
    }

    @Test
    public void testUpdateItemWithValidItem() {
        final var item = new CardSet();

        cell.updateItem(item, false);

        verify(controller).setCardSet(item);
        assertEquals(root, cell.getGraphic());
    }

    @Test
    public void testUpdateItemWithEmptyItem() {
        cell.updateItem(null, true);

        verify(controller, never()).setCardSet(any());
        assertNull(cell.getGraphic());
    }
}