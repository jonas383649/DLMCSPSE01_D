package com.iu.memorylearnapp.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StyleTest {

    @Test
    public void testResourcePathIsDefined() {
        for (final var path : Style.values()) {
            assertNotNull(path.toString());
        }
    }
}
