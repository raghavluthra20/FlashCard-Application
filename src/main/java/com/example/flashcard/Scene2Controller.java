package com.example.flashcard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene2Controller {
    @FXML
    private Label nameLabel;

    @FXML
    private Button logoutButton;

    public void setNameLabel(String name)
    {
        nameLabel.setText("Hello: " + name);
    }

    public void logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("User Log-out Confirmation");
        alert.setContentText("Are you sure you want to log out?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("Scene1.fxml"))));
        }
    }
}
