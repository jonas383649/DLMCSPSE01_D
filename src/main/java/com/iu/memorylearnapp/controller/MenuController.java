package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.renderer.MenuItemCell;
import com.iu.memorylearnapp.repositories.CardSetRepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MenuController {

    @Autowired
    private CardSetRepository cardSetRepository;

    @FXML
    private ListView<CardSet> cardSetListView;

    @FXML
    public void initialize() {
        final var cardSets = cardSetRepository.findAll();
        final var items = FXCollections.observableArrayList(cardSets);

        cardSetListView.setCellFactory(_ -> new MenuItemCell());
        cardSetListView.setItems(items);
    }

    @FXML
    public void createCardSet() {
        System.out.println("Create card set");
    }
}
