package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.common.ResourcePath;
import com.iu.memorylearnapp.events.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class StageService implements ApplicationListener<StageReadyEvent> {

    @Autowired
    private ApplicationContext context;

    private Stage stage;

    @Override
    public void onApplicationEvent(final StageReadyEvent event) {
        stage = event.getStage();
        initialize();
    }

    public void load(final ResourcePath path) {
        final var loader = new FXMLLoader(getClass().getResource(path.toString()));
        loader.setControllerFactory(context::getBean);

        try {
            final var scene = new Scene(loader.load(), 600, 400);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void initialize() {
        stage.setTitle("Memory Learn App");
        load(ResourcePath.MENU_VIEW);
    }
}
