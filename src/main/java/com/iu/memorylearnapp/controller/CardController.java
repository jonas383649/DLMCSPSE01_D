package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.Card;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;

/**
 * Controller that contains the required logic of the memory card view.
 */
@Controller
@Scope("prototype")
public class CardController {

    private final String revealed = "revealed";

    private final String highlighted = "highlighted";

    @Autowired
    private GameController gameController;

    @FXML
    private Button button;

    @FXML
    private ImageView imageView;

    @FXML
    private Label content;

    private Card card;

    private PauseTransition coverDelay;

    /**
     * Executes the initialization logic of the controller to set up the view elements.
     */
    @FXML
    public void initialize() {
        configCoverDelay();
        updateStyles();
    }

    /**
     * Reveal the card and use the game controller to check whether a pair of cards has been found.
     */
    @FXML
    public void reveal() {
        performReveal();
    }

    /**
     * Cover the card.
     */
    @FXML
    public void cover() {
        coverDelay.play();
    }

    /**
     * Remove the marking of a revealed card.
     */
    public void unmark() {
        button.getStyleClass().remove(highlighted);
    }

    /**
     * Get the associated card of this controller.
     *
     * @return the current {@link Card} instance
     */
    public Card getCard() {
        return card;
    }

    /**
     * Set the new associated card of this controller and update the corresponding view elements.
     *
     * @param card the new associated {@link Card} instance
     */
    public void setCard(final Card card) {
        this.card = card;
        updateContent();
        updateImage();
    }

    private void configCoverDelay() {
        coverDelay = new PauseTransition(Duration.seconds(1.5));
        coverDelay.setOnFinished(_ -> performCover());
    }

    private void updateStyles() {
        button.setMinSize(0, 0);

        imageView.fitHeightProperty().bind(button.heightProperty().multiply(0.9).subtract(30));
        imageView.setPreserveRatio(true);
        imageView.setVisible(false);
        imageView.setManaged(false);

        content.maxWidthProperty().bind(button.widthProperty().subtract(5));
        content.setVisible(false);
    }

    private void performReveal() {
        coverDelay.stop();

        button.setDisable(true);
        button.getStyleClass().add(revealed);
        button.getStyleClass().add(highlighted);

        if (hasImage()) {
            imageView.setVisible(true);
            imageView.setManaged(true);
        }

        content.setVisible(true);

        gameController.checkCard(this);
    }

    private void performCover() {
        button.setDisable(false);
        button.getStyleClass().remove(revealed);
        button.getStyleClass().remove(highlighted);

        if (hasImage()) {
            imageView.setVisible(false);
            imageView.setManaged(false);
        }

        content.setVisible(false);
    }

    private void updateContent() {
        content.setText(card.getContent());
    }

    private void updateImage() {
        if (hasImage()) {
            final var path = card.getImagePath();
            final var url = new File(path).toURI().toString();
            final var image = new Image(url);

            imageView.setImage(image);
        }
    }

    private boolean hasImage() {
        return card.getImagePath() != null;
    }
}
