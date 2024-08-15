package com.iu.memorylearnapp.common;

public enum View {
    CARD_VIEW("/com/iu/memorylearnapp/views/card-view.fxml"),
    GAME_VIEW("/com/iu/memorylearnapp/views/game-view.fxml"),
    MENU_VIEW("/com/iu/memorylearnapp/views/menu-view.fxml"),
    MENU_ITEM_VIEW("/com/iu/memorylearnapp/views/menu-item-view.fxml");

    private final String path;

    View(final String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
