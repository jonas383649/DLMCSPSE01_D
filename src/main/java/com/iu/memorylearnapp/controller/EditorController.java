package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.entities.CardPair;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.renderer.EditorItemCell;
import com.iu.memorylearnapp.repositories.CardSetRepository;
import com.iu.memorylearnapp.services.ResourceService;
import com.iu.memorylearnapp.services.StageService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EditorController {

    @Autowired
    private CardSetRepository cardSetRepository;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private StageService stageService;

    @FXML
    private ListView<CardPair> cardPairsListView;

    private CardSet cardSet;

    @FXML
    public void initialize() {
        updateListView();
    }

    @FXML
    public void addCardPair() {
        final var cardPairs = cardSet.getCardPairs();
        final var cardPair = new CardPair(cardSet);
        cardPairs.add(cardPair);
        updateListView();
    }

    @FXML
    public void deleteCardPair(final CardPair cardPair) {
        cardSet.getCardPairs().remove(cardPair);
        updateListView();
    }

    @FXML
    public void saveChanges() {
        cardSetRepository.save(cardSet);
        this.stageService.show(View.MENU);
    }

    @FXML
    public void cancel() {
        this.stageService.show(View.MENU);
    }

    public void setCardSet(final CardSet cardSet) {
        this.cardSet = cardSet;
    }

    private void updateListView() {
        final var cardPairs = cardSet.getCardPairs();
        final var items = FXCollections.observableArrayList(cardPairs);

        cardPairsListView.setCellFactory(_ -> new EditorItemCell(resourceService));
        cardPairsListView.setItems(items);
    }
}
