package com.iu.memorylearnapp.common;

public enum Data {
    INITIAL_DATA("com/iu/memorylearnapp/data/initial_data.json");

    private final String path;

    Data(final String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
