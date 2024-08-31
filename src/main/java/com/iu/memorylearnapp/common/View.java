package com.iu.memorylearnapp.common;

public enum View {
    CARD("card-view.fxml"),
    DETAIL("detail-view.fxml"),
    DIFFICULTY("difficulty-view.fxml"),
    EDITOR("editor-view.fxml"),
    EDITOR_ITEM("editor-item-view.fxml"),
    GAME("game-view.fxml"),
    MENU("menu-view.fxml"),
    MENU_ITEM("menu-item-view.fxml"),
    STATISTIC("statistic-view.fxml");

    private static final String PATH = "/com/iu/memorylearnapp/views/";

    private final String value;

    View(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return PATH + value;
    }
}
