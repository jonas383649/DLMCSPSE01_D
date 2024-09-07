package com.iu.memorylearnapp.events;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

/**
 * Event that signifies when the JavaFX {@link Stage} is ready for use.
 * This event is used to signal that the stage has been initialized and is ready
 * to display its content.
 */
public class StageReadyEvent extends ApplicationEvent {
    public StageReadyEvent(final Stage stage) {
        super(stage);
    }

    public Stage getStage() {
        return (Stage) getSource();
    }
}
