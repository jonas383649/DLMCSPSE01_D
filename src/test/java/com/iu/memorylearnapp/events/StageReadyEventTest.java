package com.iu.memorylearnapp.events;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class StageReadyEventTest {

    @Test
    public void testStageReadyEventInitialization() {
        final var stage = mock(Stage.class);
        final var event = new StageReadyEvent(stage);
        assertEquals(stage, event.getStage());
    }
}