package com.example.crudapp.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;

public class MainWorkerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonDiagramm;

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
    private AnchorPane rootPane;

    @FXML
    void initialize() {


    }

}
