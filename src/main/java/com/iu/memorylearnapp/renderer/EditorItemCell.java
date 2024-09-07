package com.iu.memorylearnapp.renderer;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.controller.EditorItemController;
import com.iu.memorylearnapp.entities.CardPair;
import com.iu.memorylearnapp.services.ResourceService;

/**
 * A custom {@link ItemCell} for displaying {@link CardPair} items in the editor view.
 * This class extends {@link ItemCell} and uses {@link EditorItemController} to manage
 * the presentation of {@link CardPair} objects.
 */
public class EditorItemCell extends ItemCell<CardPair, EditorItemController> {

    public EditorItemCell(final ResourceService resourceService) {
        super(resourceService, View.EDITOR_ITEM);
    }

    @Override
    protected void updateItem(final CardPair item, final boolean empty) {
        if (!empty) {
            controller.setCardPair(item);
        }

        super.updateItem(item, empty);
    }
}
