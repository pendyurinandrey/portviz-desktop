package com.portviz.desktop.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("/view/main.fxml")
public class MainScreenController {

    @FXML
    private PieChart allocPerCountryPieChart;

    @FXML
    private AnchorPane centralAnchorPane;

    @FXML
    private GridPane centralGridPane;

    @FXML
    public void initialize() {
        centralGridPane.prefWidthProperty().bind(centralAnchorPane.widthProperty());
        centralGridPane.prefHeightProperty().bind(centralAnchorPane.heightProperty());
        var allocPerCountryData = FXCollections.observableArrayList(
                new PieChart.Data("Iphone 5S", 13),
                new PieChart.Data("Samsung Grand", 25),
                new PieChart.Data("MOTO G", 10),
                new PieChart.Data("Nokia Lumia", 22));
        allocPerCountryPieChart.setData(allocPerCountryData);
    }
}
