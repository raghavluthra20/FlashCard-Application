package com.example.flashcard;

import UserAdmin.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) throws IOException {
        String username = usernameText.getText();
        String password = passwordText.getText();
        if(DataService.checkLoginCredentials(username, password)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserHomeScreen.fxml"));
            UserHomeScreenController userHomeScreenController = new UserHomeScreenController();
//            userHomeScreenController.setUser(new User(username));//TODO: change COMPLETELY and integrate with server


            root = loader.load();
            stage = new Stage();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
//            userHomeScreenController.initialize();
            //TODO: to make sure same user does not login more than once
        }
        else{//TODO: integrate with gui
            System.out.println("Wrong username and/or password");
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
