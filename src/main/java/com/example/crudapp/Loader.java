package com.example.crudapp;

import com.example.crudapp.Models.SceneModel;
import com.example.crudapp.Models.StageModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Loader extends Application {
//    by sl1degod
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "123";

    @Override
    public void start(Stage stage) throws IOException {
        StageModel.setMyStage(stage);
        stage.getIcons().add(new Image(Objects.requireNonNull(Loader.class.getResourceAsStream("/img/icon.jpg"))));
        FXMLLoader fxmlLoader = new FXMLLoader(Loader.class.getResource("views/start-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SceneModel.setMyScene(scene);
        stage.setScene(scene);
        stage.show();
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