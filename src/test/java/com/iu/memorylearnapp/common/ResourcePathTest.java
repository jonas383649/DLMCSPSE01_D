package com.iu.memorylearnapp.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResourcePathTest {

    @Test
    public void testResourcePathIsDefined() {
        for (final var path : ResourcePath.values()) {
            assertNotNull(path.toString());
        }
    }
}
