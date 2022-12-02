package com.example.flashcard;

import UserAdmin.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Category;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserHomeScreenController implements Initializable{

    private User user;

    @FXML
    private Label nameLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private ListView<Category> categoryList;

    @FXML
    private TextField newCategoryName;

    @FXML
    private Button createCategoryButton;

    private Scene scene;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {//TODO: link with login page
        this.user = user;
        categoryList.getItems().addAll(getUser().getCategories());
        setNameLabel(getUser().getUsername());
    }

    public void setNameLabel(String name) {
        nameLabel.setText("Hello " + name);
    }

    public void editCategoryList(String categoryName) {
        categoryList.getItems().addAll(new Category(categoryName));
        user.setCategories(new ArrayList<>(categoryList.getItems()));
        System.out.println(user.getCategories());//TODO:remove later!
    }

    public void logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("User Log-out Confirmation");
        alert.setContentText("Are you sure you want to log out?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
    }

    public void createCategoryAction(ActionEvent event) {
        // if user enters empty string or whitespaces, then don't create a category
        if(newCategoryName.getText().trim().equals(""))
            return;

        editCategoryList(newCategoryName.getText());
        newCategoryName.setText("");
        //TODO: integrate with Category class
        //TODO: make sure ki same name waale categories na bane
    }

    public void openCategoryScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryScreenController.fxml"));
        Parent root = loader.load();//TODO: integrate with server
        Scene scene1 = new Scene(root);

        CategoryScreenController categoryScreenController = loader.getController();
        categoryScreenController.setUser(getUser());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene1);
    }

    public void initialize(){
        try{
            setNameLabel(user.getUsername());
            categoryList.getItems().addAll(user.getCategories());

        }
        catch (Exception e)//TODO:custom exception??
        {
            System.out.println("Error during listview init");
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        categoryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {

            @Override
            public void changed(ObservableValue<? extends Category> arg0, Category arg1, Category arg2) {
                Category category = categoryList.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryScreen.fxml"));
                System.out.println("ListView Item Selected");
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }//TODO: add custom exception
                CategoryScreenController categoryScreenController = loader.getController();
                categoryScreenController.setUser(getUser());
                categoryScreenController.setCategory(category);
                Scene scene = new Scene(root);
                Stage stage = (Stage) categoryList.getScene().getWindow();
                stage.setScene(scene);
            }
        });
    }

}
//TODO: activity map top contributors my contributions integrate with gui