package com.example.flashcard;

import UserAdmin.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import services.DataService;

import java.io.IOException;

public class LoginScreenController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameText;

    @FXML
    private TextField passwordText;

    @FXML
    private Button quitButton;
    @FXML
    private AnchorPane scenePane;

    @FXML
    private Label errorSuccessMessage;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) throws IOException {
        String username = usernameText.getText();
        String password = passwordText.getText();
        // check if user exists and password matches
        if(DataService.getInstance().checkLoginCredentials(username, password)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserHomeScreen.fxml"));


            root = loader.load();
            stage = new Stage();

            scene = new Scene(root);

            User user = DataService.getInstance().findUser(username);
            ((UserHomeScreenController)loader.getController()).setUser(user);//TODO: change setUser to check if user already exists
            //TODO: change COMPLETELY and integrate with server
            stage.setScene(scene);
            stage.show();
            //TODO: to make sure same user does not login more than once
        }
        else{
            System.out.println("Wrong username and/or password");
            errorSuccessMessage.setText("username and password don't match");
            errorSuccessMessage.setTextFill(Paint.valueOf("#ff0000")); // RED color
        }
    }

    public void register(ActionEvent event) {
        String username = usernameText.getText();
        String password = passwordText.getText();
        if(!DataService.getInstance().registerUser(username, password)) {
            errorSuccessMessage.setText("Username already taken.");
            errorSuccessMessage.setTextFill(Paint.valueOf("#ff0000")); // RED color
        } else {
            errorSuccessMessage.setText("User successfully created!");
            errorSuccessMessage.setTextFill(Paint.valueOf("#00ff00")); // GREEN color
        }
    }

    public void quit(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Application");
        alert.setHeaderText("Quit Application Confirmation");
        alert.setContentText("Are you sure you want to quit the application?");
        if(alert.showAndWait().get() == ButtonType.OK)
        {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("You successfully quit the application");
            stage.close();
        }

    }


}
