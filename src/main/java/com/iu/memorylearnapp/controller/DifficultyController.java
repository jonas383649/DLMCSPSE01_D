package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.StageService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DifficultyController {

    @Autowired
    private GameController gameController;

    @Autowired
    private StageService stageService;

    @FXML
    private Button easyButton;

    @FXML
    private Button mediumButton;

    @FXML
    private Button hardButton;

    private CardSet cardSet;

    @FXML
    public void initialize() {
        updateButtonStates();
    }

    @FXML
    public void playEasy() {
        play(2);
    }

    @FXML
    public void playMedium() {
        play(4);
    }

    @FXML
    public void playHard() {
        play(8);
    }

    @FXML
    public void onCancel() {
        stageService.closePopover();
    }

    public CardSet getCardSet() {
        return cardSet;
    }

    public void setCardSet(final CardSet cardSet) {
        this.cardSet = cardSet;
    }

    private void updateButtonStates() {
        final var pairs = cardSet.getCardPairs().size();

        easyButton.setDisable(pairs < 2);
        mediumButton.setDisable(pairs < 4);
        hardButton.setDisable(pairs < 8);
    }

    private void play(final int goal) {
        gameController.setCardSet(cardSet);
        gameController.setGoal(goal);

        stageService.show(View.GAME);
    }
}