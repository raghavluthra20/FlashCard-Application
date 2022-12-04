package com.example.flashcard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Card;

import java.io.IOException;

public class EditCardSceneController {

    private Scene previousScene;

    public Scene getPreviousScene() {
        return previousScene;
    }

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    private Card card;
    @FXML
    private TextArea answerText;

    @FXML
    private Button editCard;

    @FXML
    private Button goBackButton;

    @FXML
    private TextArea questionText;

    public void setCard(Card card) {
        this.card = card;
        setQuestionText();
        setAnswerText();

    }

    public String getAnswerText() {
        return answerText.getText();
    }

    public void setAnswerText() {
        answerText.setText(card.getAnswer());
    }

    public String getQuestionText() {
        return questionText.getText();
    }

    public void setQuestionText() {
        questionText.setText(card.getQuestion());
    }

    @FXML
    public void editCard(ActionEvent event) {
        if(questionText.getText().isBlank() || answerText.getText().isBlank())
        {
            return;
        }//TODO: add custom exception
        card.setQuestion(getQuestionText());
        card.setAnswer(getAnswerText());
        System.out.println(previousScene.getUserData() instanceof FXMLLoader);//TODO: custom exception
        ((DeckSceneController)((FXMLLoader)previousScene.getUserData()).getController()).setCards();
        System.out.println("card edited");
        goBackButton.fire();
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        SceneHandler.getInstance().switchToScene((Stage)((Node)event.getSource()).getScene().getWindow(),previousScene);
    }

}
