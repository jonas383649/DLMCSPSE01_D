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

/**
 * Controller that contains the required logic of the editor view.
 */
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

    /**
     * Executes the initialization logic of the controller to set up the view elements.
     */
    @FXML
    public void initialize() {
        updateListView();
    }

    /**
     * Add a new card pair to the current card set.
     */
    @FXML
    public void addCardPair() {
        final var cardPairs = cardSet.getCardPairs();
        final var cardPair = new CardPair(cardSet);
        cardPairs.add(cardPair);
        updateListView();
    }

    /**
     * Delete a card pair of the current card set.
     *
     * @param cardPair the {@link CardPair} instance to be deleted
     */
    @FXML
    public void deleteCardPair(final CardPair cardPair) {
        cardSet.getCardPairs().remove(cardPair);
        updateListView();
    }

    /**
     * Save the current changes in the database.
     */
    @FXML
    public void saveChanges() {
        cardSetRepository.save(cardSet);
        this.stageService.show(View.MENU);
    }

    /**
     * Discard the current changes and show the menu view.
     */
    @FXML
    public void cancel() {
        this.stageService.show(View.MENU);
    }

    /**
     * Set the new associated card set of this controller.
     *
     * @param cardSet the new associated {@link CardSet} instance
     */
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
