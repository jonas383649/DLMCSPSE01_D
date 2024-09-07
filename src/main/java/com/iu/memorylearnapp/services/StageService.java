package com.iu.memorylearnapp.services;

import com.iu.memorylearnapp.common.Style;
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

/**
 * Service class responsible for managing the primary {@link Stage} in the application.
 *
 * <p>The {@link StageService} handles stage initialization and scene management. It listens for
 * {@link StageReadyEvent} events to initialize the stage and provides methods for showing views
 * and popovers. The service ensures proper styling and layout management of the application window.</p>
 */
@Service
public class StageService implements ApplicationListener<StageReadyEvent> {

    @Autowired
    private ResourceService resourceService;

    private Parent parent;

    private Scene scene;

    private Stage stage;

    /**
     * Handles the {@link StageReadyEvent} to initialize the stage.
     *
     * @param event the event containing the {@link Stage} to be managed
     */
    @Override
    public void onApplicationEvent(final StageReadyEvent event) {
        stage = event.getStage();
        initialize();
    }

    /**
     * Displays the specified view on the stage.
     *
     * @param view the {@link View} to be shown
     * @throws RuntimeException if an error occurs while loading the view or setting up the scene
     */
    public void show(final View view) {
        final var loader = resourceService.createLoader(view);
        final var styles = resourceService.createUrl(Style.GLOBAL.toString()).toExternalForm();

        try {
            parent = loader.load();
            scene = new Scene(parent);
            scene.getStylesheets().add(styles);
            stage.setScene(scene);
            stage.show();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        Platform.runLater(() -> parent.requestFocus());
    }

    /**
     * Displays a popover with the specified view on the current scene.
     *
     * @param view the {@link View} to be shown in the popover
     * @throws RuntimeException if an error occurs while loading the view or setting up the popover
     */
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
    
    /**
     * Closes the currently displayed popover, reverting the scene to the original content.
     */
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
