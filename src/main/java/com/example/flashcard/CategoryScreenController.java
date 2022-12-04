package com.example.flashcard;

import UserAdmin.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Category;
import models.Deck;
import services.DataService;

import java.io.IOException;
import java.util.ArrayList;

public class CategoryScreenController {

    private User user;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        categoryNameLabel.setText(category.getName());

        // only show decks belonging to category
        ArrayList<Deck> allPublicDecks = DataService.getInstance().getPublicDecks();
        ArrayList<Deck> categoryPublicDecks = new ArrayList<>();
        for(Deck d : allPublicDecks) {
            if(d.getCategory() == category)
                categoryPublicDecks.add(d);
        }
        publicDeckList.getItems().addAll(categoryPublicDecks);


        ArrayList<Deck> privateDecks = new ArrayList<>();
        for(Deck d : user.getDecks()) {
            if(!d.isPublic()) {
                if(d.getCategory().getName().equals(category.getName()))
                    privateDecks.add(d);
            }
        }
        myDeckList.getItems().addAll(privateDecks);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        deckNumber.setText(Integer.toString(user.getDecks().size()));
    }

    @FXML
    private TextField newDeckName;
    @FXML
    private Button createPublicDeckButton;
    @FXML
    private Button createPrivateDeckButton;
    @FXML
    private ListView<Deck> myDeckList;
    @FXML
    private ListView<Deck> publicDeckList;
    @FXML
    private Button viewMyListButton;
    @FXML
    private Button reviseMyDeckButton;
    @FXML
    private Button revisePublicDeckButton;

    @FXML
    private Label categoryNameLabel;

    @FXML
    private Label deckNumber;

    @FXML
    private Button goBackButton;

    private Scene previousScene;

    public Scene getPreviousScene() {
        return previousScene;
    }

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    public void goBack(ActionEvent event) throws IOException {
        SceneHandler.getInstance().switchToScene((Stage)((Node)event.getSource()).getScene().getWindow(),previousScene);
    }

    public void createPublicDeck(ActionEvent event) {
        String name = newDeckName.getText();
        Deck deck = new Deck(name, true, category);
        DataService.getInstance().registerPublicDeck(deck);
    }

    public void createPrivateDeck(ActionEvent event) {
        String name = newDeckName.getText();
        Deck deck = new Deck(name, category);
        user.addNewDeck(deck);
    }

}
