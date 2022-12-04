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
import services.AdminService;

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

    @FXML
    private Label activityLabel;

    @FXML
    private Label contributionsLabel;

    // top 3 contributors
    @FXML
    private Label contributor_1;
    @FXML
    private Label contributor_2;
    @FXML
    private Label contributor_3;

    private Scene scene;

    public User getUser() {
        return this.user;
    }

    public void setAllContributionsAndActivity() {
        activityLabel.setText(Integer.toString(user.getActivity()));
        contributionsLabel.setText(Integer.toString(user.getContributions()));

        // set top contributors
        ArrayList<User> topUsers = AdminService.getInstance().getTopContributors();
        contributor_1.setText("1. " + topUsers.get(0).getUsername() + ": " + Integer.toString(topUsers.get(0).getContributions()));
        contributor_2.setText("2. " + topUsers.get(1).getUsername() + ": " + Integer.toString(topUsers.get(1).getContributions()));
        contributor_3.setText("3. " + topUsers.get(2).getUsername() + ": " + Integer.toString(topUsers.get(2).getContributions()));
    }

    public void setUser(User user) {//TODO: link with login page
        this.user = user;
        setCategories();
        setNameLabel(user.getUsername());

        setAllContributionsAndActivity();
    }
    public void setCategories()
    {
        categoryList.getItems().clear();
        categoryList.getItems().addAll(AdminService.getInstance().getCategories());
    }

    public void setNameLabel(String name) {
        nameLabel.setText("Hello, " + name);
    }

    public void editCategoryList(String categoryName) {
        Category newCategory = AdminService.getInstance().createNewCategory(categoryName);

        // if category already exists, then return
        if(newCategory == null)
            return;

        categoryList.getItems().addAll(newCategory);
        System.out.println(AdminService.getInstance().getCategories()); //TODO:remove later!
    }

    public void logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("User Log-out Confirmation");
        alert.setContentText("Are you sure you want to log out?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            user.setLoggedIn(false);
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
        categoryScreenController.setUser(user);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene1);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        categoryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {

            @Override
            public void changed(ObservableValue<? extends Category> arg0, Category arg1, Category arg2) {
                Category category = categoryList.getSelectionModel().getSelectedItem();
                System.out.println("ListView Item Selected");
                SceneHandler.getInstance().switchToCategoryScreen((Stage) categoryList.getScene().getWindow(),categoryList.getScene(),getUser(),category);

            }
        });
    }

}
//TODO: activity map top contributors my contributions integrate with gui