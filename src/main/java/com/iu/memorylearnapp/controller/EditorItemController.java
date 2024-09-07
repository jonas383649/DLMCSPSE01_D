package com.iu.memorylearnapp.controller;

import com.iu.memorylearnapp.entities.Card;
import com.iu.memorylearnapp.entities.CardPair;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static java.nio.file.Files.copy;
import static java.nio.file.Files.createDirectories;

/**
 * Controller that contains the required logic of the editor item view.
 */
@Controller
@Scope("prototype")
public class EditorItemController {

    @Autowired
    private EditorController editorController;

    @FXML
    private TextField firstTextField;

    @FXML
    private TextField secondTextField;

    @FXML
    private Button firstImageButton;

    @FXML
    private Button secondImageButton;

    private CardPair cardPair;

    /**
     * Executes the initialization logic of the controller to set up the view elements.
     */
    @FXML
    public void initialize() {
        firstTextField.textProperty().addListener((_, _, value) -> updateCardContent(cardPair.getFirstCard(), value));
        secondTextField.textProperty().addListener((_, _, value) -> updateCardContent(cardPair.getSecondCard(), value));

        firstImageButton.setOnAction(_ -> chooseImage(cardPair.getFirstCard()));
        secondImageButton.setOnAction(_ -> chooseImage(cardPair.getSecondCard()));
    }

    /**
     * Delete the associated card pair of this controller.
     */
    @FXML
    public void deleteCardPair() {
        editorController.deleteCardPair(cardPair);
    }

    /**
     * Set the new associated card pair of this controller.
     *
     * @param cardPair the new associated {@link CardPair} instance
     */
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
            textField.setTooltip(new Tooltip("Inhalt fehlt"));
            textField.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        } else {
            textField.setTooltip(null);
            textField.setStyle("");
        }
    }

    private void chooseImage(final Card card) {
        final var fileChooser = new FileChooser();
        final var imageFiles = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");

        fileChooser.getExtensionFilters().add(imageFiles);

        final var image = fileChooser.showOpenDialog(null);

        if (image != null) {
            saveImage(card, image);
        }
    }

    private void saveImage(final Card card, final File image) {
        final var destination = Path.of("images", image.getName());

        try {
            createDirectories(destination.getParent());
            copy(image.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            card.setImagePath(destination.toString());
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
