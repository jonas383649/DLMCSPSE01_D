package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.StageService;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controller that contains the required logic of the statistic view.
 */
@Controller
public class StatisticController {

    @Autowired
    private StageService stageService;

    @FXML
    private Text time;

    @FXML
    private Text moves;

    @FXML
    private Text bestTime;

    @FXML
    private Text bestMoves;

    @FXML
    private Text avgTime;

    @FXML
    private Text avgMoves;

    private CardSet cardSet;

    /**
     * Executes the initialization logic of the controller to set up the view elements.
     */
    @FXML
    public void initialize() {
        updateStatisticsView();
    }

    /**
     * Show the game view to replay the currently selected memory card set.
     */
    @FXML
    public void replay() {
        stageService.show(View.GAME);
    }

    /**
     * Show the menu view.
     */
    @FXML
    public void showMenu() {
        stageService.show(View.MENU);
    }

    /**
     * Set the new associated card set of this controller.
     *
     * @param cardSet the new associated {@link CardSet} instance
     */
    public void setCardSet(final CardSet cardSet) {
        this.cardSet = cardSet;
    }

    private void updateStatisticsView() {
        final var statistic = cardSet.getStatistic();

        time.setText(formatTime(statistic.getLastTime()));
        moves.setText(String.valueOf(statistic.getLastMoves()));
        bestTime.setText(formatTime(statistic.getBestTime()));
        bestMoves.setText(String.valueOf(statistic.getBestMoves()));
        avgTime.setText(formatTime(statistic.getAvgTime()));
        avgMoves.setText(String.valueOf(statistic.getAvgMoves()));
    }

    private String formatTime(final double time) {
        final int minutes = (int) (time / 60);
        final int seconds = (int) (time % 60);

        return String.format("%02d:%02d", minutes, seconds);
    }
}