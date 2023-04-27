package com.example.crudapp.Controllers;

import com.example.crudapp.DataBase.DbFunctions;
import com.example.crudapp.Models.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EntrantUpdateController {
    @FXML
    private Label ErrorLabel;

    @FXML
    private Button buttonSave;

    @FXML
    private Label labelFormEduc;

    @FXML
    private Label labelSpec;

    @FXML
    private Button buttonDelete;

    @FXML
    private ChoiceBox<String> choiceBoxStatus;

    DbFunctions dbFunctions = new DbFunctions();

    String idUser = "";

    String formId = "";

    @FXML
    void initialize() {
        setStatus();
        buttonSave.setOnAction(e -> {
            updateDataUser();
        });

        buttonDelete.setOnAction(e -> {
            deleteDataUser();
        });

    }

    private void deleteDataUser() {
        if (idUser.equals("")) {
            ErrorLabel.setText("Повторите попытку позже");
        } else {
            dbFunctions.deleteDataUser(formId, labelFormEduc.getText(), labelSpec.getText());
            System.out.println(formId);
            buttonDelete.getScene().getWindow().hide();

        }

    }

    private void updateDataUser(){
        String status = choiceBoxStatus.getValue();
        String spec = labelSpec.getText();
        dbFunctions.updateDataUser(status, idUser, spec);
        buttonSave.getScene().getWindow().hide();
    }

    public void setData(String spec, String formeduc,  String id, String formid) {
        labelSpec.setText(spec);
        labelFormEduc.setText(formeduc);
        idUser = id;
        formId = formid;
    }

    private void setStatus() {
        ObservableList<String> status = FXCollections.observableArrayList("Зачислен", "Не Зачислен", "На рассмотрении");
        choiceBoxStatus.setValue("Выберите статус");
        choiceBoxStatus.setItems(status);
        Singleton.getInstance().setStatus(choiceBoxStatus.getValue());
    }
}
