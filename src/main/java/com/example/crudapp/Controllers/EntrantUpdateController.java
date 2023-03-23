package com.example.crudapp.Controllers;

import com.example.crudapp.DataBase.DbFunctions;
import com.example.crudapp.Models.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EntrantUpdateController {
    @FXML
    private Label ErrorLabel;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonSave;

    @FXML
    private Label labelFormEduc;

    @FXML
    private Label labelSpec;

    @FXML
    private TextField textFieldStatus;

    DbFunctions dbFunctions = new DbFunctions();

    String idUser = "";

    @FXML
    void initialize() {
        buttonSave.setOnAction(e -> {
            updateDataUser();
        });

    }



    private void updateDataUser(){
        String status = textFieldStatus.getText();
        String spec = labelSpec.getText();
        dbFunctions.updateDataUser(status, idUser, spec);
        buttonSave.getScene().getWindow().hide();
    }

    public void setData(String spec, String formeduc,  String id) {
        labelSpec.setText(spec);
        labelFormEduc.setText(formeduc);
        idUser = id;
    }

}
