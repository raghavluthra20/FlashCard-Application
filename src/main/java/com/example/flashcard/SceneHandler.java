package com.example.flashcard;

import UserAdmin.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Category;
import services.DataService;

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
            ((UserHomeScreenController)loader.getController()).setUser(user);//TODO: change setUser to check if user already exists
        }
        //TODO: integrate with server (done right?)

        stage.setScene(scene);
        stage.show();

        //TODO: to make sure same user does not login more than once
    }

    public void switchToCategoryScreen(Stage stage,Scene scene, User user, Category category){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryScreen.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }//TODO: add custom exception
        CategoryScreenController categoryScreenController = loader.getController();
        categoryScreenController.setUser(user);
        categoryScreenController.setPreviousScene(scene);
        categoryScreenController.setCategory(category);
        Scene sceneNext = new Scene(root);
        stage.setScene(sceneNext);
    }

    public void switchToScene(Stage stage, Scene scene)
    {
        stage.setScene(scene);
    }
}
