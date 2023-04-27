package com.example.crudapp.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.crudapp.DataBase.DbFunctions;
import com.example.crudapp.Loader;
import com.example.crudapp.Models.Entrant;
import com.example.crudapp.Models.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class EntrantStatusController {
    @FXML
    private TableView<Entrant> tableView;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonDefault;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableColumn<?, ?> columnForEduc;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnSpec;

    @FXML
    private TableColumn<?, ?> columnStatus;

    @FXML
    private TableColumn<?, ?> columnFio;

    @FXML
    private TableColumn<?, ?> columnAvg;

    @FXML
    private ChoiceBox<String> choiceBoxFormEduc;

    @FXML
    private ChoiceBox<String> choiceBoxSpec;

    @FXML
    private ChoiceBox<String> choiceBoxCheckStatus;

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        setStatus();
        setSpec();
        setFormEduc();
        buttonBack.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/main-entrant-window.fxml", "Основное окно");
        });
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnFio.setCellValueFactory(new PropertyValueFactory<>("fio"));
        columnSpec.setCellValueFactory(new PropertyValueFactory<>("spec"));
        columnForEduc.setCellValueFactory(new PropertyValueFactory<>("formeduc"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnAvg.setCellValueFactory(new PropertyValueFactory<>("avg"));
        tableView.setItems(dbFunctions.getAllEntrantSelect(Singleton.getInstance().getAbitId()));

        buttonDefault.setOnAction(e -> tableView.setItems(dbFunctions.getAllEntrantSelect(Singleton.getInstance().getAbitId())));


        choiceBoxCheckStatus.setOnAction(e ->  tableView.setItems(dbFunctions.filter(choiceBoxCheckStatus.getValue(), choiceBoxFormEduc.getValue(), choiceBoxSpec.getValue())));
        choiceBoxFormEduc.setOnAction(e ->  tableView.setItems(dbFunctions.filter(choiceBoxCheckStatus.getValue(), choiceBoxFormEduc.getValue(), choiceBoxSpec.getValue())));
        choiceBoxSpec.setOnAction(e ->  tableView.setItems(dbFunctions.filter(choiceBoxCheckStatus.getValue(), choiceBoxFormEduc.getValue(), choiceBoxSpec.getValue())));

    }

    private void setStatus() {
        ObservableList<String> specs = FXCollections.observableArrayList("Зачислен", "Не зачислен", "На рассмотрении");
        choiceBoxCheckStatus.setValue("На рассмотрении");
        choiceBoxCheckStatus.setItems(specs);
    }

    private void setSpec() {
        ObservableList<String> specs = FXCollections.observableArrayList("Разработка и эксплуатация нефтяных и газовых месторождений",
                "Бурение нефтяных и газовых скважин", "Геофизические методы поисков и разведки месторождений полезных ископаемых", "Монтаж, техническое обслуживание и ремонт промышленного оборудования",
                "Информационные системы и программирование", "Сетевое и системное администрирование ");
        choiceBoxSpec.setValue("Разработка и эксплуатация нефтяных и газовых месторождений");
        choiceBoxSpec.setItems(specs);
    }

    private void setFormEduc() {
        ObservableList<String> formEduc = FXCollections.observableArrayList("Очная", "Заочная");
        choiceBoxFormEduc.setValue("Очная");
        choiceBoxFormEduc.setItems(formEduc);
    }

}
