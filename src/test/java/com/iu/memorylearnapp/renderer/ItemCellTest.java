package com.iu.memorylearnapp.renderer;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.services.ResourceService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemCellTest extends ApplicationTest {

    private Cell cell;

    private ResourceService resourceService;

    private Object controller;

    private Node root;

    private View view;

    @BeforeEach
    public void setUp() throws Exception {
        final var loader = mock(FXMLLoader.class);

        resourceService = mock(ResourceService.class);
        controller = mock(Object.class);
        root = mock(Node.class);
        view = mock(View.class);

        when(resourceService.createLoader(any())).thenReturn(loader);
        when(loader.getController()).thenReturn(controller);
        when(loader.load()).thenReturn(root);

        cell = new Cell();
    }

    @Test
    public void testConstructor() {
        final var controller = ReflectionTestUtils.getField(cell, "controller");
        final var root = ReflectionTestUtils.getField(cell, "root");

        assertSame(this.controller, controller);
        assertEquals(this.root, root);

        verify(resourceService).createLoader(view);
    }

    @Test
    public void testUpdateItemWhenItemIsPresent() {
        final var item = mock(Object.class);
        cell.updateItem(item, false);
        assertSame(cell.getGraphic(), root);
    }

    @Test
    public void testUpdateItemWhenItemIsEmpty() {
        cell.updateItem(null, true);
        assertNull(cell.getGraphic());
    }

    private class Cell extends ItemCell<Object, Object> {
        public Cell() {
            super(resourceService, view);
        }
    }
}