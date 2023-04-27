package com.example.crudapp.Controllers;

import java.net.URL;
import java.util.OptionalInt;
import java.util.ResourceBundle;

import com.example.crudapp.DataBase.DbFunctions;
import com.example.crudapp.DataBase.Variables;
import com.example.crudapp.Loader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;

import static java.lang.Math.min;

public class WorkerStats {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonPrint;

    @FXML
    private PieChart pieChart;

    @FXML
    private AnchorPane rootPane;

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        pieChart.setData(dbFunctions.setPieChart());
        pieChart.setTitle("Абитуриенты, проживающие в разных городах");
        pieChart.setLegendVisible(false);
        pieChart.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", ((data.getPieValue() / Variables.COUNT) * 100));
            System.out.println(Variables.COUNT);
            System.out.println(data.getPieValue());
            Tooltip tooltip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), tooltip);
        });

        buttonBack.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/worker-main-window.fxml", "Анкеты");
            Variables.COUNT = 0;
        });

        buttonPrint.setOnAction(e -> {
            buttonPrint.setVisible(false);
            buttonBack.setVisible(false);
            print(rootPane);
        });
    }

    private void print(Node node) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(null)) {
            PageLayout pageLayout = job.getJobSettings().getPageLayout();
            double scaleX = 1.0;
            if (pageLayout.getPrintableWidth() < node.getBoundsInParent().getWidth()) {
                scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
            }
            double scaleY = 1.0;
            if (pageLayout.getPrintableHeight() < node.getBoundsInParent().getHeight()) {
                scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
            }

            double scaleXY = Double.min(scaleX, scaleY);
            Scale scale = new Scale(scaleXY, scaleXY);
            node.getTransforms().add(scale);
            boolean success = job.printPage(node);
            node.getTransforms().remove(scale);
            if (success) {
                job.endJob();
                buttonPrint.setVisible(true);
                buttonBack.setVisible(true);
            }
        }
    }
}
