package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.events.StageReadyEvent;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class StageService implements ApplicationListener<StageReadyEvent> {

    @Autowired
    private ResourceService resourceService;

    private Stage stage;

    @Override
    public void onApplicationEvent(final StageReadyEvent event) {
        stage = event.getStage();
        initialize();
    }

    public void show(final View view) {
        final var loader = resourceService.createLoader(view);

        try {
            final Parent parent = loader.load();
            final Scene scene = new Scene(parent, 600, 400);
            Platform.runLater(parent::requestFocus);
            stage.setScene(scene);
            stage.show();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void initialize() {
        stage.setTitle("Memory Learn App");
        show(View.MENU_VIEW);
    }
}
