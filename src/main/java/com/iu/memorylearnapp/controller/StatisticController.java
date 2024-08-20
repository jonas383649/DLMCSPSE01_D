package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.StageService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StatisticController {

    @Autowired
    private StageService stageService;

    @FXML
    private Label timeLabel;

    @FXML
    private Label movesLabel;

    @FXML
    private Label bestTimeLabel;

    @FXML
    private Label bestMovesLabel;

    @FXML
    private Label avgTimeLabel;

    @FXML
    private Label avgMovesLabel;

    private CardSet cardSet;

    @FXML
    public void initialize() {
        updateStatisticsView();
    }

    @FXML
    public void replay() {
        stageService.show(View.GAME);
    }

    @FXML
    public void showMenu() {
        stageService.show(View.MENU);
    }

    public void setCardSet(final CardSet cardSet) {
        this.cardSet = cardSet;
    }

    private void updateStatisticsView() {
        final var statistic = cardSet.getStatistic();

        timeLabel.setText(formatTime(statistic.getLastTime()));
        movesLabel.setText(String.valueOf(statistic.getLastMoves()));
        bestTimeLabel.setText(formatTime(statistic.getBestTime()));
        bestMovesLabel.setText(String.valueOf(statistic.getBestMoves()));
        avgTimeLabel.setText(formatTime(statistic.getAvgTime()));
        avgMovesLabel.setText(String.valueOf(statistic.getAvgMoves()));
    }

    private String formatTime(final double time) {
        final int minutes = (int) (time / 60);
        final int seconds = (int) (time % 60);

        return String.format("%02d:%02d", minutes, seconds);
    }
}