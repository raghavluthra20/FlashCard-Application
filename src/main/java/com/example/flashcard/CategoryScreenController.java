package com.example.flashcard;

import UserAdmin.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Category;
import models.Deck;
import services.AdminService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CategoryScreenController implements Initializable {

    private User user;
    private Category category;

    public Category getCategory() {
        return category;
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
    @FXML
    private Button viewPublicDeckButton;

    private Scene previousScene;

    public Scene getPreviousScene() {
        return previousScene;
    }

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }


    public void SetPrivateDeckNumber() {
        int privateDeckSize = 0;
        for(Deck d : user.getDecks()) {
            if(!d.isPublic() && d.getCategory().getName().equals(category.getName()))
                privateDeckSize = privateDeckSize + 1;
        }
        deckNumber.setText(Integer.toString(privateDeckSize));
    }

    public void setCategory(Category category) {
        this.category = category;
        categoryNameLabel.setText(category.getName());

        // only show decks belonging to category
        setDecks();
        SetPrivateDeckNumber();
    }

    public void setDecks(){
        ArrayList<Deck> allPublicDecks = AdminService.getInstance().getPublicDecks();
        ArrayList<Deck> categoryPublicDecks = new ArrayList<>();
        for(Deck d : allPublicDecks) {
            if(d.getCategory() == category)
                categoryPublicDecks.add(d);
        }
        publicDeckList.getItems().clear();
        publicDeckList.getItems().addAll(categoryPublicDecks);


        ArrayList<Deck> privateDecks = new ArrayList<>();
        for(Deck d : user.getDecks()) {
            if(!d.isPublic()) {
                if(d.getCategory().getName().equals(category.getName()))
                    privateDecks.add(d);
            }
        }
        myDeckList.getItems().clear();
        myDeckList.getItems().addAll(privateDecks);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;

    }


    public void goBack(ActionEvent event) throws IOException {
        SceneHandler.getInstance().switchToScene((Stage)((Node)event.getSource()).getScene().getWindow(),previousScene);
    }

    public void createPublicDeck(ActionEvent event) {
        String name = newDeckName.getText();
        System.out.println("Create deck request public");
        Deck deck = new Deck(name, true, category);
        AdminService.getInstance().registerPublicDeck(deck);
        setDecks();
    }

    public void createPrivateDeck(ActionEvent event) {
        String name = newDeckName.getText();
        Deck deck = new Deck(name, category);
        user.addNewDeck(deck);
        setDecks();
        SetPrivateDeckNumber();
    }

    public void viewDeck(ActionEvent event){
        try
        {
            Deck deck = myDeckList.getSelectionModel().getSelectedItem();
            SceneHandler.getInstance().switchToDeckScreen((Stage) myDeckList.getScene().getWindow(), myDeckList.getScene(), deck);
        }
        catch(Exception e){System.out.println(e);}//TODO: custom exception
    }
    public void viewPublicDeck(ActionEvent event){
        Deck deck = publicDeckList.getSelectionModel().getSelectedItem();
        SceneHandler.getInstance().switchToDeckScreen((Stage) publicDeckList.getScene().getWindow(),publicDeckList.getScene(),deck);
        //TODO: custom exception
    }
    public void reviseDeck(ActionEvent event){
        Deck deck = myDeckList.getSelectionModel().getSelectedItem();
        SceneHandler.getInstance().switchToReviseDeckScreen((Stage) myDeckList.getScene().getWindow(), myDeckList.getScene(), deck,0,0);
        //TODO: custom exception
    }
    public void revisePublicDeck(ActionEvent event){
        Deck deck = publicDeckList.getSelectionModel().getSelectedItem();
            SceneHandler.getInstance().switchToReviseDeckScreen((Stage) myDeckList.getScene().getWindow(),myDeckList.getScene(),deck,0,0);
        //TODO: custom exception, abhi ke liye scenehandler mein added hai
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myDeckList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Deck>() {

            @Override
            public void changed(ObservableValue<? extends Deck> arg0, Deck arg1, Deck arg2) {

                System.out.println("myDeckListView Item Selected");
            }
        });
        publicDeckList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Deck>() {

            @Override
            public void changed(ObservableValue<? extends Deck> arg0, Deck arg1, Deck arg2) {
                Deck deck = publicDeckList.getSelectionModel().getSelectedItem();
                System.out.println("publicDeckListView Item Selected");
            }
        });
    }
}
