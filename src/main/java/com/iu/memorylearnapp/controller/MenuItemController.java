package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.common.View;
import com.iu.memorylearnapp.entities.CardSet;
import com.iu.memorylearnapp.services.StageService;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Controller that contains the required logic of the menu item view.
 */
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

    /**
     * Select the associated card set to play and open it in the difficulty view.
     */
    @FXML
    public void playCardSet() {
        difficultyController.setCardSet(cardSet);
        stageService.showPopover(View.DIFFICULTY);
    }

    /**
     * Select the associated card set to edit and open it in the editor view.
     */
    @FXML
    public void editCardSet() {
        editorController.setCardSet(cardSet);
        stageService.show(View.EDITOR);
    }

    /**
     * Set the new associated card set of this controller.
     *
     * @param cardSet the new associated {@link CardSet} instance
     */
    public void setCardSet(final CardSet cardSet) {
        this.name.setText(cardSet.getName());
        this.cardSet = cardSet;
    }
}