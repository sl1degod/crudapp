package com.example.crudapp.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.crudapp.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainEntrantController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button buttonForm;

    @FXML
    private Button buttonProfile;

    @FXML
    private Button buttonStatus, buttonBack;

    @FXML
    void initialize() {
        buttonProfile.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/entrant-profile.fxml", "Профиль");
        });

        buttonForm.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/entrant-form-window.fxml", "Анкета");
        });

        buttonStatus.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/entrant-status.fxml", "Статус");
        });

        buttonBack.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/start-window.fxml", "Вход");
        });
    }

}
