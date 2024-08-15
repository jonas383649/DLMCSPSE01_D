package com.iu.memorylearnapp.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ViewTest {

    @Test
    public void testResourcePathIsDefined() {
        for (final var path : View.values()) {
            assertNotNull(path.toString());
        }
    }
}
