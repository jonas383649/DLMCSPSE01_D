package com.iu.memorylearnapp.common;

public enum ResourcePath {
    MENU_VIEW("/com/iu/memorylearnapp/views/menu-view.fxml");

    private final String path;

    ResourcePath(final String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
