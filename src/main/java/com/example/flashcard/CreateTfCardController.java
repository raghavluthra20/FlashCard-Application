package com.example.flashcard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Card;
import models.CardType;
import models.Deck;
import models.cardFactory.CardGenerator;

import java.io.IOException;

public class CreateTfCardController {
    private Scene previousScene;

    public Scene getPreviousScene() {
        return previousScene;
    }

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    private Deck deck;

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @FXML
    private RadioButton trueRadio;
    @FXML
    private RadioButton falseRadio;

    @FXML
    private TextArea questionText;
    @FXML
    private Button createCardButton;
    @FXML
    private Button goBackButton;

    public  void createCard(ActionEvent event)
    {//TODO: custom exception
        if(questionText.getText().isBlank())
        {
            return;
        }
        String answer = null;
        if(trueRadio.isSelected())
        {
            answer = "True";
        }
        else if(falseRadio.isSelected())
        {
            answer = "False";
        }

        Card card = (new CardGenerator()).newCard(questionText.getText() , answer, deck.getCategory(), CardType.TF);
        deck.addCard(card);

        System.out.println(previousScene.getUserData() instanceof FXMLLoader); //TODO: custom exception
        ((DeckSceneController)((FXMLLoader)previousScene.getUserData()).getController()).setCards();
        System.out.println("new card added in deck");
        goBackButton.fire();
    }
    public void goBack(ActionEvent event) throws IOException {
        SceneHandler.getInstance().switchToScene((Stage)((Node)event.getSource()).getScene().getWindow(),previousScene);
    }
}
