package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.Card;
import com.iu.memorylearnapp.entities.CardPair;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class EditorItemController {

    @Autowired
    private EditorController editorController;

    @FXML
    private TextField firstTextField;

    @FXML
    private TextField secondTextField;

    private CardPair cardPair;

    @FXML
    public void initialize() {
        firstTextField.textProperty().addListener((_, _, value) -> updateCardContent(cardPair.getFirstCard(), value));
        secondTextField.textProperty().addListener((_, _, value) -> updateCardContent(cardPair.getSecondCard(), value));
    }

    @FXML
    public void deleteCardPair() {
        editorController.deleteCardPair(cardPair);
    }

    public void setCardPair(final CardPair cardPair) {
        this.cardPair = cardPair;
        updateTextFields();
    }

    private void updateCardContent(final Card card, final String value) {
        card.setContent(value);
        validateTextFields();
    }

    private void updateTextFields() {
        firstTextField.setText(cardPair.getFirstCard().getContent());
        secondTextField.setText(cardPair.getSecondCard().getContent());

        validateTextFields();
    }

    private void validateTextFields() {
        validateTextField(firstTextField);
        validateTextField(secondTextField);
    }

    private void validateTextField(final TextField textField) {
        if (textField.getText() == null || textField.getText().isEmpty()) {
            textField.setTooltip(new Tooltip("Bitte füge den Inhalt hinzu."));
            textField.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        } else {
            textField.setTooltip(null);
            textField.setStyle("");
        }
    }
}
