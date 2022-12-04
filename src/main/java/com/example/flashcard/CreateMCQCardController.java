package com.example.flashcard;

import UserAdmin.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Card;
import models.CardType;
import models.Deck;
import models.cardFactory.CardGenerator;
import services.AdminService;

import java.io.IOException;

public class CreateMCQCardController {
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
    private TextArea questionText;
    private String answer;
    private String question;

    @FXML
    private Button createCardButton;

    @FXML
    private Button goBackButton;

    @FXML
    private RadioButton optionARadio;
    @FXML
    private RadioButton optionBRadio;
    @FXML
    private RadioButton optionCRadio;
    @FXML
    private RadioButton optionDRadio;

    @FXML
    private TextField optionAText;
    @FXML
    private TextField optionBText;
    @FXML
    private TextField optionCText;
    @FXML
    private TextField optionDText;

    private User user;
    public User getUser() {
        return user;
    }

    public void setUser(String username) {
        this.user = AdminService.getInstance().findUser(username);
    }

    public  void createCard(ActionEvent event)
    {//TODO: custom exception
        writeQuestionText();
        writeAnswerText();
        if(questionText.getText().isBlank() || answer.isBlank())
        {
            return;
        }

        Card card = (new CardGenerator()).newCard(question, answer, deck.getCategory(), CardType.MCQ);

        deck.addCard(card);
        System.out.println(previousScene.getUserData() instanceof FXMLLoader);//TODO: custom exception
        ((DeckSceneController)((FXMLLoader)previousScene.getUserData()).getController()).setCards();
        System.out.println("new card added in deck");
        if(deck.isPublic())
        {
            user.setContributions(user.getContributions() + 1);
        }
        System.out.println("Contributions appended");
        goBackButton.fire();
    }
    public void writeQuestionText(){
        question = questionText.getText() + "\na) " + optionAText.getText() + "\nb) " + optionAText.getText() + "\nc) " + optionAText.getText() + "\nd) " + optionAText.getText();
    }
    public void writeAnswerText(){
        if(optionARadio.isSelected())
            answer = "a) " + optionAText.getText();
        else if(optionBRadio.isSelected())
            answer = "b) " + optionBText.getText();
        else if(optionCRadio.isSelected())
            answer = "c) " + optionCText.getText();
        else if(optionDRadio.isSelected())
            answer = "d) " + optionDText.getText();
    }

    public void goBack(ActionEvent event) throws IOException {
        SceneHandler.getInstance().switchToScene((Stage)((Node)event.getSource()).getScene().getWindow(),previousScene);
    }
}
