package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.Card;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
@Scope("prototype")
public class CardController {

    @Autowired
    private GameController gameController;

    @FXML
    private Button button;

    @FXML
    private ImageView imageView;

    @FXML
    private Text content;

    private Card card;

    private PauseTransition coverDelay;

    @FXML
    public void initialize() {
        coverDelay = new PauseTransition(Duration.seconds(1.5));
        coverDelay.setOnFinished(_ -> performCover());
    }

    @FXML
    public void reveal() {
        performReveal();
    }

    @FXML
    public void cover() {
        coverDelay.play();
    }

    public void unmark() {
        button.setBorder(null);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(final Card card) {
        this.card = card;
        updateContent();
        updateImage();
    }

    private void performReveal() {
        coverDelay.stop();

        button.setDisable(true);
        button.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

        if (hasImage()) {
            imageView.setVisible(true);
            imageView.setManaged(true);
        }

        content.setVisible(true);

        gameController.checkCard(this);
    }

    private void performCover() {
        button.setBorder(null);
        button.setDisable(false);

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
