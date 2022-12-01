package com.example.flashcard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1Controller {

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameText;

    @FXML
    private Button quitButton;
    @FXML
    private AnchorPane scenePane;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) throws IOException {
        String username = usernameText.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));

        root = loader.load();

        Scene2Controller scene2Controller = loader.getController();
        scene2Controller.setNameLabel(username);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
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
