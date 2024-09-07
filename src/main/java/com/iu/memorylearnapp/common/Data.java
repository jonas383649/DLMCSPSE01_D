package com.iu.memorylearnapp.common;

/**
 * Enum which represents data resources and their corresponding paths.
 */
public enum Data {
    INITIAL("initial_data.json");

    private static final String PATH = "/com/iu/memorylearnapp/data/";

    private final String value;

    Data(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return PATH + value;
    }
}
