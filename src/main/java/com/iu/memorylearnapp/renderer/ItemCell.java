package com.iu.memorylearnapp.renderer;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.services.ResourceService;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

/**
 * An abstract base class for custom list cells that uses a cell factory to
 * load an FXML view and its associated controller.
 *
 * @param <I> the type of the item managed by this cell
 * @param <C> the type of the controller associated with the FXML view
 */
public abstract class ItemCell<I, C> extends ListCell<I> {

    protected final C controller;

    protected final Node root;

    public ItemCell(final ResourceService resourceService, final View view) {
        final var loader = resourceService.createLoader(view);

        try {
            root = loader.load();
            controller = loader.getController();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void updateItem(final I item, final boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(root);
        }
    }
}
