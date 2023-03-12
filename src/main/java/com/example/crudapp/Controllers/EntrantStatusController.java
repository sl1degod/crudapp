package com.example.crudapp.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.crudapp.DataBase.DbFunctions;
import com.example.crudapp.Loader;
import com.example.crudapp.Models.Entrant;
import com.example.crudapp.Models.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private AnchorPane rootPane;

    @FXML
    private TableColumn<?, ?> columnForEduc;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnSpec;

    @FXML
    private TableColumn<?, ?> columnStatus;
    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        buttonBack.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/main-entrant-window.fxml", "Основное окно");
        });
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnSpec.setCellValueFactory(new PropertyValueFactory<>("spec"));
        columnForEduc.setCellValueFactory(new PropertyValueFactory<>("formeduc"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        String id = Singleton.getInstance().getId();
        tableView.setItems(dbFunctions.getAllEntrant());
    }

}
