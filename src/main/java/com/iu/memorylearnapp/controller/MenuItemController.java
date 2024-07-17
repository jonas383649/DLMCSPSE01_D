package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.CardSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.springframework.stereotype.Controller;

@Controller
public class MenuItemController {

    @FXML
    private HBox content;

    @FXML
    private Text name;

    @FXML
    private Button editButton;

    private CardSet cardSet;

    public void setCardSet(final CardSet cardSet) {
        this.cardSet = cardSet;
        this.name.setText(cardSet.getName());
    }

    @FXML
    public void playCardSet() {
        System.out.println("Play card set: " + cardSet.getName());
    }

    @FXML
    public void editCardSet() {
        System.out.println("Edit card set: " + cardSet.getName());
    }
}