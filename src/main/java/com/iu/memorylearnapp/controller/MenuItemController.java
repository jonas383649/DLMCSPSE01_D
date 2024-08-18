package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.StageService;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class MenuItemController {

    @Autowired
    private DifficultyController difficultyController;

    @Autowired
    private EditorController editorController;

    @Autowired
    private StageService stageService;

    @FXML
    private Text name;

    private CardSet cardSet;

    @FXML
    public void playCardSet() {
        difficultyController.setCardSet(cardSet);
        stageService.showPopover(View.DIFFICULTY);
    }

    @FXML
    public void editCardSet() {
        editorController.setCardSet(cardSet);
        stageService.show(View.EDITOR);
    }

    public void setCardSet(final CardSet cardSet) {
        this.name.setText(cardSet.getName());
        this.cardSet = cardSet;
    }
}