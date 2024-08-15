package com.iu.memorylearnapp.renderer;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.controller.MenuItemController;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.ResourceService;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class MenuItemCell extends ListCell<CardSet> {

    private final HBox root;

    private final MenuItemController controller;

    public MenuItemCell(final ResourceService resourceService) {
        final var loader = resourceService.createLoader(View.MENU_ITEM_VIEW);

        try {
            root = loader.load();
            controller = loader.getController();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(final CardSet item, final boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            controller.setCardSet(item);
            setGraphic(root);
        }
    }
}