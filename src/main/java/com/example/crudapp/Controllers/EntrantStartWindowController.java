package com.example.crudapp.Controllers;



import com.example.crudapp.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class EntrantStartWindowController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button buttonRegistration;

    @FXML
    private Button buttonSignIn;

    @FXML
    private Button buttonBack;


    @FXML
    void initialize() {
        buttonBack.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/start-window.fxml", "Абитуриент");
        });
        buttonRegistration.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/registration-window.fxml", "Регистрация");
        });

        buttonSignIn.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/entrant-auth.fxml", "Авторизация");
        });
    }




}
