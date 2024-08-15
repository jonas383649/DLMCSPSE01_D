package com.iu.memorylearnapp.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataTest {

    @Test
    public void testResourcePathIsDefined() {
        for (final var path : Data.values()) {
            assertNotNull(path.toString());
        }
    }
}
