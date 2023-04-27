package com.example.crudapp.Controllers;


import com.example.crudapp.DataBase.DbFunctions;
import com.example.crudapp.Loader;
import com.example.crudapp.Models.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class EntrantProfileController {

    @FXML
    private Button buttonBack;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView image;

    @FXML
    private Label labelCity;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPatronymic;

    @FXML
    private Label labelSurname;

    @FXML
    private Label labeldatebirthday;

    @FXML
    private Label labelAvgScore;

    @FXML
    private Label labelPhone;

    @FXML
    private Label labelExit;

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        info();
        buttonBack.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/main-entrant-window.fxml", "Главное окно");
        });

        labelExit.setOnMouseClicked(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/start-window.fxml", "Начальный экран");
        });
    }

    private void info() {
        String login = Singleton.getInstance().getLogin();

        dbFunctions.setInformation(login);
        labelName.setText(Singleton.getInstance().getName());
        labelSurname.setText(Singleton.getInstance().getSurname());
        labelPatronymic.setText(Singleton.getInstance().getPatronymic());
        labelCity.setText(Singleton.getInstance().getCity());
        labelPhone.setText(Singleton.getInstance().getPhone());
        image.setImage(new Image(Singleton.getInstance().getImage()));
        labeldatebirthday.setText(Singleton.getInstance().getDateBirthday());
        labelAvgScore.setText(Singleton.getInstance().getAvgScore());
    }

}
