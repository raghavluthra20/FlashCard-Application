package com.example.flashcard;

import UserAdmin.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Card;
import models.Category;
import models.Deck;

import java.io.IOException;


public class SceneHandler {

    private static SceneHandler instance;

    synchronized public static SceneHandler getInstance() {
        if(instance == null)
            instance = new SceneHandler();

        return instance;
    }
    public void launchLoginScreen(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(root);

        Image icon = new Image("icon.png");
        stage.setTitle("Flash Card Application");
        stage.getIcons().add(icon);
        stage.setResizable(false);
//        stage.setFullScreenExitHint("");
//        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUserHomeScreen(User user,Stage stage,Scene scene) throws IOException {
        FXMLLoader loader;
        if(stage == null)stage = new Stage();

        if(scene == null)
        {
            loader = new FXMLLoader(getClass().getResource("UserHomeScreen.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
            UserHomeScreenController userHomeScreenController = (UserHomeScreenController) loader.getController();
            userHomeScreenController.setUser(user);
        }
        //TODO: integrate with server (done right?)

        stage.setScene(scene);
        Image icon = new Image("icon.png");
        stage.setTitle(user.getUsername());
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.show();

        //TODO: to make sure same user does not login more than once
    }

    public void switchToCategoryScreen(Stage stage,Scene previousScene, User user, Category category){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryScreen.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }//TODO: add custom exception
        CategoryScreenController categoryScreenController = loader.getController();
        categoryScreenController.setUser(user);
        categoryScreenController.setPreviousScene(previousScene);
        categoryScreenController.setCategory(category);
        Scene nextScene = new Scene(root);
        stage.setScene(nextScene);
    }
    public void switchToDeckScreen(Stage stage, Scene previousScene, Deck deck)
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeckScene.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }//TODO: custom exception
        DeckSceneController deckSceneController = loader.getController();
        deckSceneController.setDeck(deck);

        String username = stage.getTitle();
        deckSceneController.setUser(username);

        deckSceneController.setPreviousScene(previousScene);
        Scene nextScene = new Scene(root);
        nextScene.setUserData(loader);

        stage.setScene(nextScene);
    }
    public void switchToReviseDeckScreen(Stage stage, Scene previousScene,Deck deck,int cardNumber, int counterChange)
    {
        if(deck == null || deck.getSize() == 0)
            return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReviseDeckScene.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }//TODO: custom exception
        ReviseDeckSceneController reviseDeckSceneController = loader.getController();
        reviseDeckSceneController.setDeck(deck,cardNumber);

        String username = stage.getTitle();
        reviseDeckSceneController.setUser(username);

        reviseDeckSceneController.setPreviousScene(previousScene);
        reviseDeckSceneController.setInitialCountInt(counterChange);
        Scene nextScene = new Scene(root);
        stage.setScene(nextScene);

    }

    public void switchToCreateDefCardScene(Stage stage,Scene previousScene,Deck deck)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateDefCardScene.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }//TODO: custom exception
        CreateDefCardController createDefCardController = loader.getController();
        createDefCardController.setDeck(deck);

        String username = stage.getTitle();
        createDefCardController.setUser(username);

        createDefCardController.setPreviousScene(previousScene);
        Scene nextScene = new Scene(root);
        stage.setScene(nextScene);
    }
    public void switchToCreateFIBCardScene(Stage stage,Scene previousScene,Deck deck)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateFIBCard.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }//TODO: custom exception
        CreateFIBCardController createFIBCardController = loader.getController();

        String username = stage.getTitle();
        createFIBCardController.setUser(username);


        createFIBCardController.setDeck(deck);
        createFIBCardController.setPreviousScene(previousScene);
        Scene nextScene = new Scene(root);
        stage.setScene(nextScene);
    }

    public void switchToCreateTfCardScene(Stage stage,Scene previousScene,Deck deck)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateTfCardScene.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }//TODO: custom exception
        CreateTfCardController createTfCardController = loader.getController();
        createTfCardController.setDeck(deck);

        String username = stage.getTitle();
        createTfCardController.setUser(username);


        createTfCardController.setPreviousScene(previousScene);
        Scene nextScene = new Scene(root);
        stage.setScene(nextScene);
    }
    public void switchToCreateMCQCardScene(Stage stage,Scene previousScene,Deck deck)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateMCQCard.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }//TODO: custom exception
        CreateMCQCardController createMCQCardController = loader.getController();
        createMCQCardController.setDeck(deck);

        String username = stage.getTitle();
        createMCQCardController.setUser(username);


        createMCQCardController.setPreviousScene(previousScene);
        Scene nextScene = new Scene(root);
        stage.setScene(nextScene);
    }

    public void switchToEditCardScene(Stage stage, Scene previousScene, Card card)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCardScene.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }//TODO: custom exception
        EditCardSceneController editCardSceneController = loader.getController();
        editCardSceneController.setCard(card);
        editCardSceneController.setPreviousScene(previousScene);
        Scene nextScene = new Scene(root);
        stage.setScene(nextScene);
    }

    public void switchToScene(Stage stage, Scene scene)
    {
        stage.setScene(scene);
    }
}
