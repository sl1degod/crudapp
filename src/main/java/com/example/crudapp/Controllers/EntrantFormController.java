package com.example.crudapp.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.crudapp.DataBase.DbFunctions;
import com.example.crudapp.Loader;
import com.example.crudapp.Models.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class EntrantFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceBoxFormEduc;

    @FXML
    private ChoiceBox<String> choiceBoxSpec;

    @FXML
    private Button buttonBack, buttonSave;

    @FXML
    private Label labelAvgScore;

    @FXML
    private Label labelDateBirthday;

    @FXML
    private Label labelName;

    @FXML
    private Label labelSurName;

    @FXML
    private Label labelpatronymic;


    @FXML
    private ImageView image;

    @FXML
    private AnchorPane rootPane;

    DbFunctions dbFunctions = new DbFunctions();




    @FXML
    void initialize() {
        setSpecs();
        setFormEduc();
        info();
        buttonBack.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/main-entrant-window.fxml", "123");
        });

        buttonSave.setOnAction(e -> {
            String abitId = Singleton.getInstance().getId();
            String spec = choiceBoxSpec.getValue();
            String formEduc = choiceBoxFormEduc.getValue();
            dbFunctions.createForm(abitId, spec, formEduc);
        });


    }

    private void setSpecs() {
        ObservableList<String> specs = FXCollections.observableArrayList("Разработка и эксплуатация нефтяных и газовых месторождений",
                "Бурение нефтяных и газовых скважин", "Геофизические методы поисков и разведки месторождений полезных ископаемых", "Монтаж, техническое обслуживание и ремонт промышленного оборудования",
                "Информационные системы и программирование", "Сетевое и системное администрирование ");
        choiceBoxSpec.setValue("Выберите специальность");
        choiceBoxSpec.setItems(specs);
        Singleton.getInstance().setSpec(choiceBoxSpec.getValue());
    }

    private void setFormEduc() {
        ObservableList<String> formEduc = FXCollections.observableArrayList("Очная", "Заочная");
        choiceBoxFormEduc.setValue("Выберите форму обучения");
        choiceBoxFormEduc.setItems(formEduc);
        Singleton.getInstance().setFormEduc(choiceBoxFormEduc.getValue());
    }

    private void info() {
        dbFunctions.setInformation(Singleton.getInstance().getLogin());
        labelSurName.setText(Singleton.getInstance().getSurname());
        labelName.setText(Singleton.getInstance().getName());
        labelpatronymic.setText(Singleton.getInstance().getPatronymic());
        labelDateBirthday.setText(Singleton.getInstance().getDateBirthday());
        labelAvgScore.setText(Singleton.getInstance().getAvgScore());
        image.setImage(new Image(Singleton.getInstance().getImage()));
    }


}
