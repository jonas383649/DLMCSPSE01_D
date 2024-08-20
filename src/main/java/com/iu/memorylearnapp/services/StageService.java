package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.events.StageReadyEvent;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class StageService implements ApplicationListener<StageReadyEvent> {

    @Autowired
    private ResourceService resourceService;

    private Parent parent;

    private Scene scene;

    private Stage stage;

    @Override
    public void onApplicationEvent(final StageReadyEvent event) {
        stage = event.getStage();
        initialize();
    }

    public void show(final View view) {
        final var loader = resourceService.createLoader(view);

        try {
            parent = loader.load();
            scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        Platform.runLater(() -> parent.requestFocus());
    }

    public void showPopover(final View view) {
        final var loader = resourceService.createLoader(view);

        try {
            final var content = new StackPane((Node) loader.load());
            final var popover = new Group(content);
            final var overlay = new Pane();

            final var width = Bindings.multiply(scene.widthProperty(), 0.75);
            final var height = Bindings.multiply(scene.heightProperty(), 0.75);

            content.prefWidthProperty().bind(width);
            content.prefHeightProperty().bind(height);

            content.setStyle("-fx-background-color: white; -fx-border-color: black;");
            overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

            overlay.setOnMouseClicked(_ -> closePopover());

            scene.setRoot(new StackPane(parent, overlay, popover));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void closePopover() {
        scene.setRoot(new StackPane(parent));
    }

    private void initialize() {
        stage.setTitle("Memory Learn App");
        stage.setHeight(700);
        stage.setWidth(1000);

        show(View.MENU);
    }
}
