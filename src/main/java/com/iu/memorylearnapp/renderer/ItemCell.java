package com.iu.memorylearnapp.renderer;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.services.ResourceService;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public abstract class ItemCell<T, C> extends ListCell<T> {

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

    protected void updateItem(final T item, final boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(root);
        }
    }
}
