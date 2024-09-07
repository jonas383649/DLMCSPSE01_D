package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.StageService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controller that contains the required logic of the difficulty view.
 */
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

    /**
     * Executes the initialization logic of the controller to set up the view elements.
     */
    @FXML
    public void initialize() {
        updateButtonStates();
    }

    /**
     * Show the game dialog and set the difficulty to easy.
     */
    @FXML
    public void playEasy() {
        play(2);
    }

    /**
     * Show the game dialog and set the difficulty to medium.
     */
    @FXML
    public void playMedium() {
        play(4);
    }

    /**
     * Show the game dialog and set the difficulty to hard.
     */
    @FXML
    public void playHard() {
        play(8);
    }

    /**
     * Cancel the difficulty selection and close the popover.
     */
    @FXML
    public void onCancel() {
        stageService.closePopover();
    }

    /**
     * Get the associated card set of this controller.
     *
     * @return the current {@link CardSet} instance
     */
    public CardSet getCardSet() {
        return cardSet;
    }

    /**
     * Set the new associated card set of this controller.
     *
     * @param cardSet the new associated {@link CardSet} instance
     */
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