package com.example.crudapp.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.crudapp.DataBase.DbFunctions;
import com.example.crudapp.Loader;
import com.example.crudapp.Models.Entrant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button buttonUpdate;

    @FXML
    private TableColumn<?, ?> columnFormEduc;

    @FXML
    private TableColumn<?, ?> columnFormSpec;

    @FXML
    private TableColumn<?, ?> columnFormStatus;

    @FXML
    private TableColumn<?, ?> columnID;

    @FXML
    private TableView<Entrant> tableView;

    @FXML
    private AnchorPane rootPane;

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize(){
        back.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/start-window.fxml", "Вход");
        });


        buttonUpdate.setOnAction(e -> tableView.setItems(dbFunctions.getAllEntrant()));


        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnFormSpec.setCellValueFactory(new PropertyValueFactory<>("spec"));
        columnFormEduc.setCellValueFactory(new PropertyValueFactory<>("formeduc"));
        columnFormStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(dbFunctions.getAllEntrant());

        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Entrant entrant = tableView.getSelectionModel().getSelectedItem();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/crudapp/views/entrant-update.fxml"));
                    Parent parent = loader.load();
                    EntrantUpdateController entrantUpdateController = loader.getController();
                    entrantUpdateController.setData(entrant.getSpec(), entrant.getFormeduc(), entrant.getId());
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

}
