package com.iu.memorylearnapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class MemoryLearnUI extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = new SpringApplicationBuilder(MemoryLearnApp.class).run();
    }

    @Override
    public void start(final Stage stage) throws IOException {
        final var loader = new FXMLLoader(MemoryLearnUI.class.getResource("/com/iu/memorylearnapp/views/menu-view.fxml"));
        loader.setControllerFactory(context::getBean);

        final var scene = new Scene(loader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }
}