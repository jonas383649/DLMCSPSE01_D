package com.iu.memorylearnapp.common;

/**
 * Enum which represents style resources and their corresponding paths.
 */
public enum Style {
    GLOBAL("global.css");

    private static final String PATH = "/com/iu/memorylearnapp/styles/";

    private final String value;

    Style(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return PATH + value;
    }
}
