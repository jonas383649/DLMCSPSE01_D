package com.iu.memorylearnapp.renderer;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.controller.MenuItemController;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.ResourceService;

/**
 * A custom {@link ItemCell} for displaying {@link CardSet} items in the menu view.
 * This class extends {@link ItemCell} and uses {@link MenuItemController} to manage
 * the presentation of {@link CardSet} objects.
 */
public class MenuItemCell extends ItemCell<CardSet, MenuItemController> {

    public MenuItemCell(final ResourceService resourceService) {
        super(resourceService, View.MENU_ITEM);
    }

    @Override
    protected void updateItem(final CardSet item, final boolean empty) {
        if (!empty) {
            controller.setCardSet(item);
        }

        super.updateItem(item, empty);
    }
}