package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.renderer.MenuItemCell;
import com.iu.memorylearnapp.repositories.CardSetRepository;
import com.iu.memorylearnapp.services.ResourceService;
import com.iu.memorylearnapp.services.StageService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controller that contains the required logic of the menu view.
 */
@Controller
public class MenuController {

    @Autowired
    private CardSetRepository cardSetRepository;

    @Autowired
    private EditorController editorController;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private StageService stageService;

    @FXML
    private ListView<CardSet> cardSetListView;

    /**
     * Executes the initialization logic of the controller to set up the view elements.
     */
    @FXML
    public void initialize() {
        final var cardSets = cardSetRepository.findAll();
        final var items = FXCollections.observableArrayList(cardSets);

        cardSetListView.setCellFactory(_ -> new MenuItemCell(resourceService));
        cardSetListView.setItems(items);
    }

    /**
     * Create a new card set entity and show it in the editor view.
     */
    @FXML
    public void createCardSet() {
        editorController.setCardSet(new CardSet());
        stageService.show(View.EDITOR);
    }
}
