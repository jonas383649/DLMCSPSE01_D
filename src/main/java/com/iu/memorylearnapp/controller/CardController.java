package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.Card;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class CardController {

    @Autowired
    private GameController gameController;

    @FXML
    private Button button;

    private Card card;

    private PauseTransition coverDelay;

    @FXML
    public void initialize() {
        coverDelay = new PauseTransition(Duration.seconds(1.5));
        coverDelay.setOnFinished(_ -> performCover());
    }

    @FXML
    public void reveal() {
        coverDelay.stop();
        button.setDisable(true);
        button.setText(card.getContent());
        button.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        gameController.checkCard(this);
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
    }

    private void performCover() {
        button.setText(null);
        button.setBorder(null);
        button.setDisable(false);
    }
}