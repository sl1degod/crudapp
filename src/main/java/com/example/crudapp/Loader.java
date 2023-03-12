package com.example.crudapp;

import com.example.crudapp.Models.SceneModel;
import com.example.crudapp.Models.StageModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Loader extends Application {
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "123";

    @Override
    public void start(Stage stage) throws IOException {
        StageModel.setMyStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(Loader.class.getResource("views/start-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SceneModel.setMyScene(scene);
        stage.setTitle("Способ входа");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void openNewScene(AnchorPane root, String window, String title) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(window)));
            root.getChildren().setAll(anchorPane);
            StageModel.getMyStage().setTitle(title);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}