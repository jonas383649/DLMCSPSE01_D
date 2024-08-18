package com.iu.memorylearnapp.common;

public enum View {
    CARD("/com/iu/memorylearnapp/views/card-view.fxml"),
    DIFFICULTY("/com/iu/memorylearnapp/views/difficulty-view.fxml"),
    EDITOR("/com/iu/memorylearnapp/views/editor-view.fxml"),
    EDITOR_ITEM("/com/iu/memorylearnapp/views/editor-item-view.fxml"),
    GAME("/com/iu/memorylearnapp/views/game-view.fxml"),
    MENU("/com/iu/memorylearnapp/views/menu-view.fxml"),
    MENU_ITEM("/com/iu/memorylearnapp/views/menu-item-view.fxml");

    private final String path;

    View(final String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
