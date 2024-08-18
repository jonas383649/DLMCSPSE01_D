package com.iu.memorylearnapp.renderer;

import com.iu.memorylearnapp.controller.EditorItemController;
import com.iu.memorylearnapp.entities.CardPair;
import com.iu.memorylearnapp.services.ResourceService;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditorItemCellTest extends ApplicationTest {

    private EditorItemCell cell;

    private EditorItemController controller;

    @BeforeEach
    public void setUp() throws Exception {
        final var loader = mock(FXMLLoader.class);
        final var resourceService = mock(ResourceService.class);
        final var root = mock(HBox.class);

        controller = mock(EditorItemController.class);

        when(resourceService.createLoader(any())).thenReturn(loader);
        when(loader.getController()).thenReturn(controller);
        when(loader.load()).thenReturn(root);

        cell = new EditorItemCell(resourceService);
    }

    @Test
    public void testUpdateItemWhenItemIsPresent() {
        final var item = new CardPair();
        cell.updateItem(item, false);
        verify(controller).setCardPair(item);
    }

    @Test
    public void testUpdateItemWhenItemIsEmpty() {
        cell.updateItem(null, true);
        verify(controller, never()).setCardPair(any());
    }
}