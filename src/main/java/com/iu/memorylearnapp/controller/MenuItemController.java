package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.StageService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class MenuItemController {

    @Autowired
    private GameController gameController;

    @Autowired
    private StageService stageService;

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
        gameController.setCardSet(cardSet);
        gameController.setGoal(2);
        stageService.show(View.GAME_VIEW);
    }

    @FXML
    public void editCardSet() {
        System.out.println("Edit card set: " + cardSet.getName());
    }
}