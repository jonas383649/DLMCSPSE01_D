package com.iu.memorylearnapp;

import com.iu.memorylearnapp.events.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * The main JavaFX application class for the Memory Learn application.
 *
 * <p>This class extends {@link Application} and integrates Spring Boot with JavaFX. It creates
 * the Spring application context, starts the JavaFX application, and manages the lifecycle of
 * both JavaFX and Spring Boot components.</p>
 */
public class MemoryLearnUI extends Application {

    private ConfigurableApplicationContext context;

    /**
     * Initializes the Spring application context.
     */
    @Override
    public void init() {
        context = new SpringApplicationBuilder(MemoryLearnApp.class).run();
    }

    /**
     * Starts the JavaFX application and publishes a {@link StageReadyEvent}.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(final Stage stage) {
        context.publishEvent(new StageReadyEvent(stage));
    }

    /**
     * Closes the Spring application context and exits the JavaFX application.
     */
    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }
}