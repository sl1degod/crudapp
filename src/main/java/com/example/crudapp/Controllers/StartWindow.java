package com.example.crudapp.Controllers;

import com.example.crudapp.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class StartWindow {


    @FXML
    private Button buttonEntrant;

    @FXML
    private Button buttonWorker;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void initialize() {
        buttonEntrant.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/entrant-start-window.fxml", "Абитуриент");
        });

        buttonWorker.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/worker-auth.fxml", "Сотрудник");
        });
    }

}
