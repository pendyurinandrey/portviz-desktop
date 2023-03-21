package com.portviz.desktop.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("/view/main.fxml")
public class PortfolioSummaryController {

    @FXML
    private PieChart allocPerCountryPieChart;

    @FXML
    public void initialize() {
        var allocPerCountryData = FXCollections.observableArrayList(
                new PieChart.Data("Iphone 5S", 13),
                new PieChart.Data("Samsung Grand", 25),
                new PieChart.Data("MOTO G", 10),
                new PieChart.Data("Nokia Lumia", 22));
        allocPerCountryPieChart.setData(allocPerCountryData);
    }
}
