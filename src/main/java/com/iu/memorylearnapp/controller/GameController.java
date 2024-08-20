package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.entities.Card;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.ResourceService;
import com.iu.memorylearnapp.services.StageService;
import com.iu.memorylearnapp.services.StatisticService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.shuffle;

@Controller
public class GameController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private StatisticController statisticController;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private StageService stageService;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label movesLabel;

    @FXML
    private Label pairsLabel;

    @FXML
    private Label timeLabel;

    private CardController selected;

    private CardSet cardSet;

    private Timer timer;

    private int moves;

    private int pairs;

    private int time;

    private int goal;

    @FXML
    public void initialize() {
        selected = null;
        moves = 0;
        pairs = 0;
        time = 0;

        updateMovesLabel();
        updatePairsLabel();
        updateTimeLabel();

        createCardsView();
    }

    @FXML
    public void quitGame() {
        stageService.show(View.MENU);
        stopTimer();
    }

    public void checkCard(final CardController controller) {
        if (selected == null) {
            selectCard(controller);
        } else {
            checkMatch(controller);
        }
    }

    public void setCardSet(CardSet cardSet) {
        this.cardSet = cardSet;
    }

    public void setGoal(final int goal) {
        this.goal = goal;
    }

    private void selectCard(final CardController controller) {
        selected = controller;
        moves++;
        updateMovesLabel();

        if (timer == null) {
            startTimer();
        }
    }

    private void checkMatch(final CardController controller) {
        final var first = controller.getCard().getCardPair();
        final var second = selected.getCard().getCardPair();

        if (first == second) {
            foundMatch(controller);
        } else {
            foundNoMatch(controller);
        }

        if (pairs == goal) {
            stopTimer();
            stopGame();
        }
    }

    private void foundMatch(final CardController controller) {
        pairs++;
        updatePairsLabel();
        controller.unmark();
        selected.unmark();
        selected = null;
    }

    private void foundNoMatch(final CardController controller) {
        controller.cover();
        selected.cover();
        selected = null;
    }

    private void stopGame() {
        statisticController.setCardSet(cardSet);
        statisticService.updateStatistic(cardSet, time, moves);
        stageService.showPopover(View.STATISTIC);
    }

    private void updateMovesLabel() {
        Platform.runLater(() -> movesLabel.setText("Züge: " + moves));
    }

    private void updatePairsLabel() {
        Platform.runLater(() -> pairsLabel.setText("Paare: " + pairs + "/" + goal));
    }

    private void updateTimeLabel() {
        Platform.runLater(() -> timeLabel.setText("Zeit: " + time + "s"));
    }

    private void createCardsView() {
        final var cards = getCards(cardSet, goal);
        final int columns = (int) Math.ceil(Math.sqrt(goal * 2));

        for (int i = 0; i < cards.size(); i++) {
            final var card = cards.get(i);
            final var cardView = createCardView(card);
            gridPane.add(cardView, i % columns, i / columns);
        }
    }

    private Node createCardView(final Card card) {
        final var loader = resourceService.createLoader(View.CARD);

        try {
            final Node root = loader.load();
            final CardController controller = loader.getController();
            controller.setCard(card);
            return root;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Card> getCards(final CardSet cardSet, final int totalPairs) {
        final var pairs = cardSet.getCardPairs().stream()
            .limit(totalPairs)
            .toList();

        final var cards = pairs.stream()
            .flatMap(pair -> Stream.of(pair.getFirstCard(), pair.getSecondCard()))
            .collect(Collectors.toList());

        shuffle(cards);

        return cards;
    }

    private void startTimer() {
        final var task = createTimerTask();
        timer = new Timer();
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private TimerTask createTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                time++;
                updateTimeLabel();
            }
        };
    }
}