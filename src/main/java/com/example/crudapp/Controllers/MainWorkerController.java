package com.example.crudapp.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.crudapp.DataBase.DbFunctions;
import com.example.crudapp.Loader;
import com.example.crudapp.Models.Entrant;
import com.example.crudapp.Models.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainWorkerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Button buttonStats;

    @FXML
    private Button buttonDefault;

    @FXML
    private TableColumn<?, ?> columnFormEduc;

    @FXML
    private TableColumn<?, ?> columnFormSpec;

    @FXML
    private TableColumn<?, ?> columnFormStatus;

    @FXML
    private TableColumn<?, ?> columnID;

    @FXML
    private TableColumn<?, ?> columnFio;

    @FXML
    private TableColumn<?, ?> columnAvg;

    @FXML
    private TableView<Entrant> tableView;

    @FXML
    private ChoiceBox<String> choiceBoxFormEduc;

    @FXML
    private ChoiceBox<String> choiceBoxSpec;

    @FXML
    private ChoiceBox<String> choiceBoxCheckStatus;

    @FXML
    private AnchorPane rootPane;

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize(){
        setStatus();
        setSpec();
        setFormEduc();
        back.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/start-window.fxml", "Вход");
        });

        buttonStats.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/worker-stats.fxml", "Статистика");
        });

        buttonDefault.setOnAction(e -> tableView.setItems(dbFunctions.getAllEntrant()));

        tableView.setItems(dbFunctions.filter(choiceBoxCheckStatus.getValue(), choiceBoxFormEduc.getValue(), choiceBoxSpec.getValue()));

        choiceBoxCheckStatus.setOnAction(e ->  tableView.setItems(dbFunctions.filter(choiceBoxCheckStatus.getValue(), choiceBoxFormEduc.getValue(), choiceBoxSpec.getValue())));
        choiceBoxFormEduc.setOnAction(e ->  tableView.setItems(dbFunctions.filter(choiceBoxCheckStatus.getValue(), choiceBoxFormEduc.getValue(), choiceBoxSpec.getValue())));
        choiceBoxSpec.setOnAction(e ->  tableView.setItems(dbFunctions.filter(choiceBoxCheckStatus.getValue(), choiceBoxFormEduc.getValue(), choiceBoxSpec.getValue())));


        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnFio.setCellValueFactory(new PropertyValueFactory<>("fio"));
        columnFormSpec.setCellValueFactory(new PropertyValueFactory<>("spec"));
        columnFormEduc.setCellValueFactory(new PropertyValueFactory<>("formeduc"));
        columnFormStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnAvg.setCellValueFactory(new PropertyValueFactory<>("avg"));
        tableView.setItems(dbFunctions.getAllEntrant());

        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Entrant entrant = tableView.getSelectionModel().getSelectedItem();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/crudapp/views/entrant-update.fxml"));
                    Parent parent = loader.load();
                    EntrantUpdateController entrantUpdateController = loader.getController();
                    entrantUpdateController.setData(entrant.getSpec(), entrant.getFormeduc(), entrant.getId(), entrant.getFormid());
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(parent));
                    stage.setTitle("Редактирование пользователя");
                    stage.showAndWait();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

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
