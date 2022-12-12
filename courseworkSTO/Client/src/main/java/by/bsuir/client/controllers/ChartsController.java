package by.bsuir.client.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.time.LocalDate;

public class ChartsController {
    @FXML
    private BarChart barChart3Days;

    @FXML
    private BarChart<LocalDate, Integer> barChartMonth;

    @FXML
    private BarChart<LocalDate, Integer> barChartWeek;

    @FXML
    private Tab tab3Days;

    @FXML
    private Tab tabMonth;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabWeek;


    public void initialize() {
//        categoryAxis3Days = new CategoryAxis();
//        numberAxis3Days = new NumberAxis();
//        barChart3Days = new BarChart(categoryAxis3Days, numberAxis3Days);
//        XYChart.Series series1 = new XYChart.Series<>();
//        series1.setName("Подъемник 1");
//        series1.getData().add(new XYChart.Data("12.12.2022", 2));
//        series1.getData().add(new XYChart.Data("13.12.2022", 4));
//        barChart3Days.getData().addAll(series1);
////        LocalDate.of(2022, 12, 13)
//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
//        barChart3Days = new BarChart<String,Number>(xAxis,yAxis);
//        barChart3Days.setTitle("Country Summary");
//        xAxis.setLabel("Country");
//        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data("austria", 25601.34));
        series1.getData().add(new XYChart.Data("brazil", 20148.82));
        series1.getData().add(new XYChart.Data("france", 10000));
        series1.getData().add(new XYChart.Data("italy", 35407.15));
        series1.getData().add(new XYChart.Data("usa", 12000));


        barChart3Days.getData().addAll(series1);
    }
}
