package com.example.flashcard;

import UserAdmin.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.Category;

public class CategoryScreenController {

    private User user;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private TextField newDeckName;
    @FXML
    private Button createPublicDeckButton;
    @FXML
    private Button createPrivateDeckButton;
    @FXML
    private ListView<String> myDeckList;
    @FXML
    private ListView<String> otherPublicDeckList;
    @FXML
    private Button viewMyListButton;
    @FXML
    private Button reviseMyDeckButton;
    @FXML
    private Button revisePublicDeckButton;

    @FXML
    private Label categoryNameLabel;



}
